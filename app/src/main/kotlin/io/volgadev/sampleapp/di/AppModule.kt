package io.volgadev.sampleapp.di

import kotlinx.serialization.json.Json as SerializationJson
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import org.koin.dsl.module

private const val TIME_OUT = 60_000

val AppModule = module(createdAtStart = true) {

    fun defaultHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(SerializationJson {
                    isLenient = true
                    ignoreUnknownKeys = true
                })
                engine {
                    connectTimeout = TIME_OUT
                    socketTimeout = TIME_OUT
                }
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d(this.toString(), "HTTP Client $message")
                    }
                }
                level = LogLevel.BODY
            }
            install(DefaultRequest) {
            }
        }
    }

    factory<HttpClient> { defaultHttpClient() }
}
