package com.b.nsofronovic.bitcoinwallet.dagger

import androidx.lifecycle.ViewModel
import com.b.nsofronovic.bitcoinwallet.ui.authenticationsetup.AuthenticationSetupViewModel
import com.b.nsofronovic.bitcoinwallet.ui.contactdetail.ContactDetailViewModel
import com.b.nsofronovic.bitcoinwallet.ui.contactlist.ContactsViewModel
import com.b.nsofronovic.bitcoinwallet.ui.dashboard.DashboardViewModel
import com.b.nsofronovic.bitcoinwallet.ui.mnemonicseed.MnemonicSeedViewModel
import com.b.nsofronovic.bitcoinwallet.ui.receivetransaction.ReceiveTransactionViewModel
import com.b.nsofronovic.bitcoinwallet.ui.sendtransaction.SendTransactionViewModel
import com.b.nsofronovic.bitcoinwallet.ui.splashscreen.SplashScreenViewModel
import com.b.nsofronovic.bitcoinwallet.ui.transactiondetails.TransactionDetailViewModel
import com.b.nsofronovic.bitcoinwallet.ui.walletname.WalletNameViewModel
import com.b.nsofronovic.bitcoinwallet.ui.walletoverview.WalletOverviewViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthenticationSetupViewModel::class)
    abstract fun bindAuthenticationViewModel(viewModel: AuthenticationSetupViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WalletNameViewModel::class)
    abstract fun bindWalletNameViewModel(viewModel: WalletNameViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MnemonicSeedViewModel::class)
    abstract fun bindMnemonicSeedViewModel(viewModel: MnemonicSeedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WalletOverviewViewModel::class)
    abstract fun bindWalletOverviewView(viewModel: WalletOverviewViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    abstract fun bindSplashScreenViewModel(viewModel: SplashScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(viewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContactsViewModel::class)
    abstract fun bindContactsViewModel(viewModel: ContactsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContactDetailViewModel::class)
    abstract fun bindContactDetailViewModel(viewModel: ContactDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SendTransactionViewModel::class)
    abstract fun bindSendTransactionViewModel(viewModel: SendTransactionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReceiveTransactionViewModel::class)
    abstract fun bindReceiveTransactionViewModel(viewModel: ReceiveTransactionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionDetailViewModel::class)
    abstract fun bindTransactionDetailViewModel(viewModel: TransactionDetailViewModel): ViewModel
}