package com.b.nsofronovic.bitcoinwallet.ui.splashscreen

import androidx.lifecycle.ViewModel
import com.b.nsofronovic.bitcoinwallet.settings.SettingsConstants
import com.b.nsofronovic.bitcoinwallet.settings.SettingsConstants.Companion.IS_WALLET_CREATED
import com.b.nsofronovic.bitcoinwallet.settings.SettingsManager
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var settingsManager: SettingsManager

    fun isWalletCreated(): Boolean? {
        return settingsManager.getBoolean(key = IS_WALLET_CREATED)
    }
}