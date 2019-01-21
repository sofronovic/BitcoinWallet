package com.b.nsofronovic.bitcoinwallet.database.AppDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.b.nsofronovic.bitcoinwallet.database.dao.IContactDao
import com.b.nsofronovic.bitcoinwallet.database.dao.IWalletDao
import com.b.nsofronovic.bitcoinwallet.model.AuthTypeConverter
import com.b.nsofronovic.bitcoinwallet.model.Contact
import com.b.nsofronovic.bitcoinwallet.model.Wallet

@Database(entities = [Contact::class, Wallet::class], version = 1, exportSchema = true)
@TypeConverters(AuthTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDao(): IContactDao

    abstract fun walletDao(): IWalletDao
}