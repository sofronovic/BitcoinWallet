package com.b.nsofronovic.bitcoinwallet.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.b.nsofronovic.bitcoinwallet.database.dao.IWalletDao
import com.b.nsofronovic.bitcoinwallet.model.Wallet
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class WalletRepository @Inject constructor(private var walletDao: IWalletDao) {

    fun getWallet(): Single<Wallet> {
        return walletDao.get()
    }

    fun insertWallet(wallet: Wallet) {
        return walletDao.insert(wallet)
    }

    fun deleteWallet(wallet: Wallet) {
        return walletDao.delete(wallet)
    }
}