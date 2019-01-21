package com.b.nsofronovic.bitcoinwallet.service

import com.b.nsofronovic.bitcoinwallet.model.AuthenticationType
import com.b.nsofronovic.bitcoinwallet.model.WalletWizard
import org.bitcoinj.wallet.DeterministicSeed
import javax.inject.Inject

class WizardService @Inject constructor(private var walletWizard: WalletWizard) {

    fun setWalletName(name: String) {
        walletWizard.name = name
    }

    fun setWalletAuthentication(authenticationType: AuthenticationType){
        walletWizard.authenticationType = authenticationType
    }

    fun setWalletAddress(address: String) {
        walletWizard.address = address
    }

    fun getWalletWizardData(): WalletWizard {
        return walletWizard
    }
}