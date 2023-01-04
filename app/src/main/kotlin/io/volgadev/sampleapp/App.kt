package io.volgadev.sampleapp

import android.app.Application
import io.volgadev.sampleapp.feature.dzentopicspicker.di.AssetsListModule
import io.volgadev.sampleapp.feature.questionnaire.di.QuestionnaireModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                /* app */

                /* core */

                /* feature */
                AssetsListModule,
                QuestionnaireModule
            )
        }
    }
}
