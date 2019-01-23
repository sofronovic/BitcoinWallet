package com.b.nsofronovic.bitcoinwallet.ui.walletoverview

import androidx.lifecycle.ViewModel
import com.b.nsofronovic.bitcoinwallet.model.WalletWizard
import com.b.nsofronovic.bitcoinwallet.repository.WalletRepository
import com.b.nsofronovic.bitcoinwallet.service.WalletService
import com.b.nsofronovic.bitcoinwallet.service.WizardService
import com.b.nsofronovic.bitcoinwallet.settings.SettingsConstants
import com.b.nsofronovic.bitcoinwallet.settings.SettingsConstants.Companion.IS_WALLET_CREATED
import com.b.nsofronovic.bitcoinwallet.settings.SettingsConstants.Companion.WALLET_NAME
import com.b.nsofronovic.bitcoinwallet.settings.SettingsManager
import io.reactivex.Completable
import javax.inject.Inject

class WalletOverviewViewModel @Inject constructor(private val repository: WalletRepository): ViewModel() {

    @Inject
    lateinit var wizardService: WizardService

    @Inject
    lateinit var walletService: WalletService

    @Inject
    lateinit var settingsManager: SettingsManager

    fun getWalletWizard(): WalletWizard {
        return wizardService.getWalletWizardData()
    }

    fun createWallet(): Completable {
        return Completable.fromAction {
            repository.insertWallet(walletService.createWallet(wizardService.getWalletWizardData()))
        }
    }

    fun getWalletAddress() {
        wizardService.setWalletAddress(walletService.getWalletAddress())
    }

    fun setWalletCreated() {
        settingsManager.setBoolean(key = IS_WALLET_CREATED, value = true)
    }

    fun setWalletName(walletName: String) {
        settingsManager.setString(WALLET_NAME, walletName)
    }
}