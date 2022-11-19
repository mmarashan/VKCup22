package io.volgadev.sampleapp.feature.prices_list.di

import io.volgadev.sampleapp.feature.prices_list.data.PricesRepositoryImpl
import io.volgadev.sampleapp.feature.prices_list.data.mapper.AssetsMapper
import io.volgadev.sampleapp.feature.prices_list.data.mapper.AssetsMapperImpl
import io.volgadev.sampleapp.feature.prices_list.domain.PricesRepository
import io.volgadev.sampleapp.feature.prices_list.navigation.AssetsListApi
import io.volgadev.sampleapp.feature.prices_list.navigation.AssetsListImpl
import io.volgadev.sampleapp.feature.prices_list.presentation.AssetsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AssetsListModule = module(createdAtStart = false) {

    factory<PricesRepository> { PricesRepositoryImpl(api = get(), assetsMapper = get()) }

    factory<AssetsListApi> { AssetsListImpl() }

    factory<AssetsMapper> { AssetsMapperImpl() }

    viewModel { AssetsListViewModel(pricesRepository = get()) }
}

