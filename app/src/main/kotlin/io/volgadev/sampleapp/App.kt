package io.volgadev.sampleapp

import android.app.Application
import io.volgadev.sampleapp.di.NetworkModule
import io.volgadev.sampleapp.feature.prices_list.di.AssetsListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@App)
            modules(
                AssetsListModule,
                NetworkModule
            )
        }
    }
}
