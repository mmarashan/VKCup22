package io.volgadev.sampleapp

import android.app.Application
import io.volgadev.sampleapp.di.AppModule
import io.volgadev.sampleapp.feature.dzentopicspicker.di.AssetsListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                /* app */
                AppModule,

                /* core */

                /* feature */
                AssetsListModule,
            )
        }
    }
}
