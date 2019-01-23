package com.b.nsofronovic.bitcoinwallet.ui.sendtransaction

import androidx.lifecycle.ViewModel
import com.b.nsofronovic.bitcoinwallet.service.WalletService
import com.b.nsofronovic.bitcoinwallet.settings.SettingsConstants
import com.b.nsofronovic.bitcoinwallet.settings.SettingsManager
import io.reactivex.Single
import javax.inject.Inject

class SendTransactionViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var walletService: WalletService

    @Inject
    lateinit var settingsManager: SettingsManager

    fun sendTransaction(amount: String, address: String): Single<Boolean> =
        walletService.sendTransaction(walletService.loadWalletFromFile(getWalletName()!!), amount, address)

    private fun getWalletName(): String? {
        return settingsManager.getString(SettingsConstants.WALLET_NAME)
    }
}