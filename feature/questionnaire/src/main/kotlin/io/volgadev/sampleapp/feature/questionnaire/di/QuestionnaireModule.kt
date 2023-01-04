package io.volgadev.sampleapp.feature.questionnaire.di

import io.volgadev.sampleapp.feature.questionnaire.data.QuestionnaireDemoRepositoryImpl
import io.volgadev.sampleapp.feature.questionnaire.domain.QuestionnaireDemoRepository
import io.volgadev.sampleapp.feature.questionnaire.navigation.QuestionnaireApi
import io.volgadev.sampleapp.feature.questionnaire.navigation.QuestionnaireImpl
import org.koin.dsl.module

val QuestionnaireModule = module(createdAtStart = false) {

    factory<QuestionnaireDemoRepository> { QuestionnaireDemoRepositoryImpl() }

    factory<QuestionnaireApi> { QuestionnaireImpl() }

    // viewModel { QuestionnaireDemoViewModel(questionnaireRepository = get()) }
}

