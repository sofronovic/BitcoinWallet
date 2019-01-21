package com.b.nsofronovic.bitcoinwallet.application

import android.app.Application
import com.b.nsofronovic.bitcoinwallet.dagger.*

class App : Application() {

    companion object {
        @JvmStatic lateinit var applicationComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        val wizardServiceModule = WizardServiceModule()
        val walletServiceModule = WalletServiceModule()

        applicationComponent = DaggerAppComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .roomModule(RoomModule(this))
            .walletServiceModule(walletServiceModule)
            .wizardServiceModule(wizardServiceModule)
            .build()

        applicationComponent.inject(this)
    }
}