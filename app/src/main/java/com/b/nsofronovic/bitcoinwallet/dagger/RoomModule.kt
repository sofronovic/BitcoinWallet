package com.b.nsofronovic.bitcoinwallet.dagger

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.b.nsofronovic.bitcoinwallet.database.AppDatabase.AppDatabase
import com.b.nsofronovic.bitcoinwallet.database.dao.IContactDao
import com.b.nsofronovic.bitcoinwallet.database.dao.IWalletDao
import com.b.nsofronovic.bitcoinwallet.repository.ContactRepository
import com.b.nsofronovic.bitcoinwallet.settings.SettingsManager
import com.b.nsofronovic.bitcoinwallet.ui.CustomViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule() {

    private lateinit var appDatabase: AppDatabase

    constructor(context: Context) : this() {
        this.appDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideContactDao(appDatabase: AppDatabase): IContactDao {
        return appDatabase.contactDao()
    }

    @Singleton
    @Provides
    fun provideWalletDao(appDatabase: AppDatabase): IWalletDao {
        return appDatabase.walletDao()
    }

    @Provides
    @Singleton
    fun provideContactRepository(IContactDao: IContactDao): ContactRepository {
        return ContactRepository(IContactDao)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return appDatabase
    }

    @Provides
    @Singleton
    fun provideSettingsManager(context: Context): SettingsManager {
        return SettingsManager(context)
    }

    /*@Provides
    @Singleton
    fun provideViewModelFactory(contactRepository: ContactRepository): ViewModelProvider.Factory {
        return CustomViewModelFactory(contactRepository)
    }*/
}