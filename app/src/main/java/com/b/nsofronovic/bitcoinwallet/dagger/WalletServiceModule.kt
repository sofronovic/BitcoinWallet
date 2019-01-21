package com.b.nsofronovic.bitcoinwallet.dagger

import android.content.Context
import com.b.nsofronovic.bitcoinwallet.service.WalletService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WalletServiceModule {

    @Provides
    @Singleton
    fun provideWalletService(context: Context): WalletService {
        return WalletService(context)
    }
}