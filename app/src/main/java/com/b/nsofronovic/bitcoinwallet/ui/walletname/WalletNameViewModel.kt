package com.b.nsofronovic.bitcoinwallet.ui.walletname

import androidx.lifecycle.ViewModel
import com.b.nsofronovic.bitcoinwallet.service.WalletService
import com.b.nsofronovic.bitcoinwallet.service.WizardService
import javax.inject.Inject

class WalletNameViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var walletService: WalletService

    @Inject
    lateinit var wizardService: WizardService

    fun storeWalletNameInWizard(walletName: String) {
         wizardService.setWalletName(walletName)
    }

    fun setupWallet(walletName: String) {
        walletService.setupWallet(walletName)
    }
}

