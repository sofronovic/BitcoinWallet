package com.b.nsofronovic.bitcoinwallet.ui.authenticationsetup

import androidx.lifecycle.ViewModel
import com.b.nsofronovic.bitcoinwallet.model.AuthenticationType
import com.b.nsofronovic.bitcoinwallet.model.Wallet
import com.b.nsofronovic.bitcoinwallet.model.WalletWizard
import com.b.nsofronovic.bitcoinwallet.repository.WalletRepository
import com.b.nsofronovic.bitcoinwallet.service.WalletService
import com.b.nsofronovic.bitcoinwallet.service.WizardService
import io.reactivex.Completable
import javax.inject.Inject

class AuthenticationSetupViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var walletWizard: WizardService

    fun setAuthentication(authenticationType: AuthenticationType) {
        walletWizard.setWalletAuthentication(authenticationType)
    }

}