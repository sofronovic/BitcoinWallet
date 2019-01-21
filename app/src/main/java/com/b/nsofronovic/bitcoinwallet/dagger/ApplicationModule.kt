package com.b.nsofronovic.bitcoinwallet.dagger

import android.app.Application
import android.content.Context
import com.b.nsofronovic.bitcoinwallet.application.App
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(app: App) {

    private var application: Application = app

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    fun provideContext(): Context {
        return application
    }
}