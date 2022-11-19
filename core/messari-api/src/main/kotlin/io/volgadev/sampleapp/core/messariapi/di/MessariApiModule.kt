package io.volgadev.sampleapp.core.messariapi.di

import io.volgadev.sampleapp.core.messariapi.data.MessariApi
import io.volgadev.sampleapp.core.messariapi.data.MessariApiImpl
import org.koin.dsl.module

val MessariApiModule = module(createdAtStart = false) {

    factory<MessariApi> { MessariApiImpl(client = get()) }
}