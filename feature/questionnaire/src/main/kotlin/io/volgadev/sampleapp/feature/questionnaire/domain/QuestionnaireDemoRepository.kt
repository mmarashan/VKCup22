package io.volgadev.sampleapp.feature.questionnaire.domain

import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType

internal interface QuestionnaireDemoRepository {

    fun getQuizItems(): List<QuestionType.Quiz>

    fun getPairMappingItems(): List<QuestionType.PairMapping>

    fun getRatingItems(): List<QuestionType.Rating>

    fun getGapFillingItems(): List<QuestionType.GapFilling>
}
