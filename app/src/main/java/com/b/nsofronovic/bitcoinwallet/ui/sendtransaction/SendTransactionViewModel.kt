package com.b.nsofronovic.bitcoinwallet.ui.sendtransaction

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.b.nsofronovic.bitcoinwallet.service.WalletService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class SendTransactionViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var walletService: WalletService

    fun sendTransaction(amount: String, address: String): Single<Boolean> = walletService.sendTransaction(amount, address)
}