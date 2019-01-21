package com.b.nsofronovic.bitcoinwallet.ui.mnemonicseed

import androidx.lifecycle.ViewModel
import com.b.nsofronovic.bitcoinwallet.application.App
import com.b.nsofronovic.bitcoinwallet.service.WalletService
import com.b.nsofronovic.bitcoinwallet.service.WizardService
import org.bitcoinj.wallet.DeterministicSeed
import javax.inject.Inject

class MnemonicSeedViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var walletService: WalletService

    fun getMnemonicPhrase(): String? {
        return walletService.getMnemonicSeed()?.joinToString()
    }
}