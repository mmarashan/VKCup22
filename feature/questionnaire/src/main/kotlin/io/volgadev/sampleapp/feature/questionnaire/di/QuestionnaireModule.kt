package io.volgadev.sampleapp.feature.questionnaire.di

import io.volgadev.sampleapp.feature.questionnaire.data.QuestionnaireRepositoryImpl
import io.volgadev.sampleapp.feature.questionnaire.domain.QuestionnaireRepository
import io.volgadev.sampleapp.feature.questionnaire.navigation.QuestionnaireApi
import io.volgadev.sampleapp.feature.questionnaire.navigation.QuestionnaireImpl
import io.volgadev.sampleapp.feature.questionnaire.presentation.topicspicker.QuestionnaireViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val QuestionnaireModule = module(createdAtStart = false) {

    factory<QuestionnaireRepository> { QuestionnaireRepositoryImpl() }

    factory<QuestionnaireApi> { QuestionnaireImpl() }

    viewModel { QuestionnaireViewModel(questionnaireRepository = get()) }
}

