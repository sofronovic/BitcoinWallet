package com.b.nsofronovic.bitcoinwallet.dagger

import com.b.nsofronovic.bitcoinwallet.application.App
import com.b.nsofronovic.bitcoinwallet.service.WalletService
import com.b.nsofronovic.bitcoinwallet.service.WizardService
import com.b.nsofronovic.bitcoinwallet.ui.authenticationsetup.AuthenticationSetupView
import com.b.nsofronovic.bitcoinwallet.ui.contactdetail.ContactDetailView
import com.b.nsofronovic.bitcoinwallet.ui.contactlist.ContactsView
import com.b.nsofronovic.bitcoinwallet.ui.dashboard.DashboardView
import com.b.nsofronovic.bitcoinwallet.ui.mnemonicseed.MnemonicSeedView
import com.b.nsofronovic.bitcoinwallet.ui.sendtransaction.SendTransactionView
import com.b.nsofronovic.bitcoinwallet.ui.splashscreen.SplashScreenView
import com.b.nsofronovic.bitcoinwallet.ui.walletname.WalletNameView
import com.b.nsofronovic.bitcoinwallet.ui.walletoverview.WalletOverviewView
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RoomModule::class,
    ApplicationModule::class,
    WizardServiceModule::class,
    WalletServiceModule::class,
    ViewModelModule::class])
interface AppComponent {

    fun inject(app: App)

    fun inject (contactsView: ContactsView)

    fun inject (mnemonicSeedView: MnemonicSeedView)

    fun inject (walletNameView: WalletNameView)

    fun inject (authenticationSetupView: AuthenticationSetupView)

    fun inject (walletOverviewView: WalletOverviewView)

    fun inject (splashScreenView: SplashScreenView)

    fun inject (dashboardView: DashboardView)

    fun inject (contactDetailView: ContactDetailView)

    fun inject (sendTransactionView: SendTransactionView)

    fun wizardService(): WizardService

    fun walletService(): WalletService
}