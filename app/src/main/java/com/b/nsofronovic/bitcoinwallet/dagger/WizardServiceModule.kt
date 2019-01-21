package com.b.nsofronovic.bitcoinwallet.dagger

import com.b.nsofronovic.bitcoinwallet.model.WalletWizard
import com.b.nsofronovic.bitcoinwallet.service.WizardService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WizardServiceModule {

    @Provides
    @Singleton
    fun provideWalletWizard(): WalletWizard {
        return WalletWizard()
    }

    @Provides
    @Singleton
    fun provideWizardService(walletWizard: WalletWizard): WizardService {
        return WizardService(walletWizard)
    }
}