package com.b.nsofronovic.bitcoinwallet.dagger

import androidx.lifecycle.ViewModelProvider
import com.b.nsofronovic.bitcoinwallet.ui.CustomViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactoryModule: CustomViewModelFactory): ViewModelProvider.Factory
}