package com.b.nsofronovic.bitcoinwallet.ui.sendtransaction

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.b.nsofronovic.bitcoinwallet.service.WalletService
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class SendTransactionViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var walletService: WalletService

    fun sendTransaction(amount: String, address: String): Completable {
        return Completable.fromAction{
            walletService.sendTransaction(amount, address)
        }
    }
}