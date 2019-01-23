package com.b.nsofronovic.bitcoinwallet.ui.dashboard

import androidx.lifecycle.ViewModel
import com.b.nsofronovic.bitcoinwallet.repository.WalletRepository
import com.b.nsofronovic.bitcoinwallet.service.WalletService
import com.b.nsofronovic.bitcoinwallet.settings.SettingsConstants
import com.b.nsofronovic.bitcoinwallet.settings.SettingsManager
import io.reactivex.Single
import org.bitcoinj.wallet.WalletTransaction
import javax.inject.Inject

class DashboardViewModel @Inject constructor(private val repository: WalletRepository) : ViewModel() {

    @Inject
    lateinit var walletService: WalletService

    fun loadWalletFromDatabase(): Single<com.b.nsofronovic.bitcoinwallet.model.Wallet> {
        return repository.getWallet()
    }

}