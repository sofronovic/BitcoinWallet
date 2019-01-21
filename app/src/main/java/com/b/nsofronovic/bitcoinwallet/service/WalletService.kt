package com.b.nsofronovic.bitcoinwallet.service

import android.content.Context
import android.util.Log
import com.b.nsofronovic.bitcoinwallet.model.WalletWizard
import com.b.nsofronovic.bitcoinwallet.rx.RxBus
import com.b.nsofronovic.bitcoinwallet.rx.RxEvent
import org.bitcoinj.core.*
import org.bitcoinj.core.listeners.DownloadProgressTracker
import org.bitcoinj.kits.WalletAppKit
import org.bitcoinj.params.TestNet3Params
import org.bitcoinj.wallet.DeterministicSeed
import org.bitcoinj.wallet.Wallet
import java.util.*
import javax.inject.Inject

class WalletService @Inject constructor(private val context: Context) {

    private lateinit var walletAppKit: WalletAppKit


    fun setupWallet(walletName: String) {
        walletAppKit = object :  WalletAppKit(getTestNetwork(), context.filesDir, walletName) {

            override fun onSetupCompleted() {
                super.onSetupCompleted()

                if (wallet().importedKeys.size < 1) {
                    wallet().importKey(ECKey())
                }

                wallet().allowSpendingUnconfirmedTransactions()
            }
        }

        walletAppKit.setDownloadListener(object : DownloadProgressTracker(){
            override fun progress(pct: Double, blocksSoFar: Int, date: Date?) {
                super.progress(pct, blocksSoFar, date)

                RxBus.publish(RxEvent.DownloadPercentage(pct))
            }

            override fun doneDownload() {
                super.doneDownload()

                RxBus.publish(RxEvent.DownloadStatus(true))
            }
        })

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
        return walletAppKit.wallet().freshReceiveAddress().toString()
    }

    fun createWallet(wizard: WalletWizard): com.b.nsofronovic.bitcoinwallet.model.Wallet {
        return com.b.nsofronovic.bitcoinwallet.model.Wallet(
            null,
            wizard.address!!,
            wizard.name!!,
            wizard.authenticationType!!
        )
    }

    fun sendTransaction(amountToSend: String, receiveAddress: String) {

    }
}