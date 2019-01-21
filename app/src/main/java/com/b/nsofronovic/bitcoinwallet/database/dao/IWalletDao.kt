package com.b.nsofronovic.bitcoinwallet.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.b.nsofronovic.bitcoinwallet.model.Wallet
import io.reactivex.Single

@Dao
interface IWalletDao {

    @Query("SELECT * from wallet LIMIT 1")
    fun get(): Single<Wallet>

    @Insert
    fun insert(vararg wallet: Wallet)

    @Delete
    fun delete(wallet: Wallet)
}