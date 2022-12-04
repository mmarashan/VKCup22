package io.volgadev.sampleapp.feature.dzentopicspicker.di

import io.volgadev.sampleapp.feature.dzentopicspicker.data.DzenTopicsRepositoryImpl
import io.volgadev.sampleapp.feature.dzentopicspicker.domain.DzenTopicsRepository
import io.volgadev.sampleapp.feature.dzentopicspicker.navigation.DzenTopicsPickerApi
import io.volgadev.sampleapp.feature.dzentopicspicker.navigation.DzenTopicsPickerImpl
import io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.DzenTopicsPickerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AssetsListModule = module(createdAtStart = false) {

    factory<DzenTopicsRepository> { DzenTopicsRepositoryImpl() }

    factory<DzenTopicsPickerApi> { DzenTopicsPickerImpl() }

    viewModel { DzenTopicsPickerViewModel(dzenTopicsRepository = get()) }
}

