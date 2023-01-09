package io.volgadev.sampleapp.feature.questionnaire.di

import io.volgadev.sampleapp.feature.questionnaire.data.QuestionnaireDemoRepositoryImpl
import io.volgadev.sampleapp.feature.questionnaire.domain.QuestionnaireDemoRepository
import io.volgadev.sampleapp.feature.questionnaire.navigation.QuestionnaireApi
import io.volgadev.sampleapp.feature.questionnaire.navigation.QuestionnaireImpl
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.GapFillingViewModel
import io.volgadev.sampleapp.feature.questionnaire.presentation.quiz.QuestionnaireQuizViewModel
import io.volgadev.sampleapp.feature.questionnaire.presentation.rating.QuestionnaireRatingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val QuestionnaireModule = module(createdAtStart = false) {

    factory<QuestionnaireDemoRepository> { QuestionnaireDemoRepositoryImpl() }

    factory<QuestionnaireApi> { QuestionnaireImpl() }

    viewModel { QuestionnaireQuizViewModel(repository = get()) }

    viewModel { QuestionnaireRatingViewModel(repository = get()) }

    viewModel { GapFillingViewModel(repository = get()) }
}

