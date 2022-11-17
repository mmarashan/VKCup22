package io.volgadev.sampleapp.feature.prices_list.di

import io.volgadev.sampleapp.feature.prices_list.data.PricesRepositoryImpl
import io.volgadev.sampleapp.feature.prices_list.domain.PricesRepository
import io.volgadev.sampleapp.feature.prices_list.navigation.AssetsListApi
import io.volgadev.sampleapp.feature.prices_list.navigation.AssetsListImpl
import org.koin.dsl.module

val AssetsListModule = module(createdAtStart = false) {

    factory<PricesRepository> { PricesRepositoryImpl(api = get()) }

    factory<AssetsListApi> { AssetsListImpl() }
}

