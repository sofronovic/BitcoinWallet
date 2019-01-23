package com.b.nsofronovic.bitcoinwallet.service

import android.content.Context
import android.util.Log
import com.b.nsofronovic.bitcoinwallet.model.WalletWizard
import com.b.nsofronovic.bitcoinwallet.rx.RxBus
import com.b.nsofronovic.bitcoinwallet.rx.RxEvent
import io.reactivex.Single
import org.bitcoinj.core.*
import org.bitcoinj.core.listeners.DownloadProgressTracker
import org.bitcoinj.kits.WalletAppKit
import org.bitcoinj.params.TestNet3Params
import org.bitcoinj.wallet.SendRequest
import org.bitcoinj.wallet.Wallet
import java.io.File
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class WalletService @Inject constructor(private val context: Context) {

    private lateinit var walletAppKit: WalletAppKit

    fun setupWallet(walletName: String) {
        walletAppKit = object : WalletAppKit(getTestNetwork(), context.filesDir, walletName) {

            override fun onSetupCompleted() {
                super.onSetupCompleted()

                if (wallet().importedKeys.size < 1) {
                    wallet().importKey(ECKey())
                }

                wallet().allowSpendingUnconfirmedTransactions()
            }
        }

        walletAppKit.setDownloadListener(object : DownloadProgressTracker() {
            override fun progress(pct: Double, blocksSoFar: Int, date: Date?) {
                super.progress(pct, blocksSoFar, date)

                RxBus.publish(RxEvent.DownloadPercentage(pct))
            }

            override fun doneDownload() {
                super.doneDownload()

                RxBus.publish(RxEvent.DownloadStatus(true))
            }
        })
        //walletAppKit.setCheckpoints( ReadProperties.class.getClassLoader().getResourceAsStream("checkpoints-bitcoin"));
        walletAppKit.setBlockingStartup(false)
        walletAppKit.startAsync()
    }

    fun getTestNetwork(): TestNet3Params? {
        return TestNet3Params.get()
    }

    fun getMnemonicSeed(): MutableList<String>? {
        return walletAppKit.wallet().keyChainSeed.mnemonicCode
    }

    fun getWalletAddress(): String {
        return walletAppKit.wallet().freshReceiveAddress().toBase58()
    }

    fun createWallet(wizard: WalletWizard): com.b.nsofronovic.bitcoinwallet.model.Wallet {
        return com.b.nsofronovic.bitcoinwallet.model.Wallet(
            null,
            wizard.address!!,
            wizard.name!!,
            wizard.authenticationType!!
        )
    }

    fun getCurrentWalletKit(): WalletAppKit {
        return walletAppKit
    }

    fun sendTransaction(amountToSend: String, destinationAddress: String): Single<Boolean> {
        return Single.create{
            try {
                org.bitcoinj.core.Context.getOrCreate(getTestNetwork())

                val filePath = File(context.filesDir.parent + "/files/" + "0.001.wallet")

                val wallet = Wallet.loadFromFile(filePath)

                val sendRequest = SendRequest
                    .to(Address.fromBase58(getTestNetwork(), destinationAddress), Coin.parseCoin(amountToSend))
                wallet.completeTx(sendRequest)
                wallet.commitTx(sendRequest.tx)

                PeerGroup(getTestNetwork()).broadcastTransaction(sendRequest.tx).broadcast()
                it.onSuccess(true)
            } catch (ex: InsufficientMoneyException) {
                it.onError(ex)
            } catch (ex: Exception) {
                Log.d("Exception", ex.message)
                it.onError(ex)
            }
        }
    }
}