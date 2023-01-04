package io.volgadev.sampleapp.feature.questionnaire.data

import io.volgadev.sampleapp.feature.questionnaire.domain.QuestionnaireDemoRepository
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType

internal class QuestionnaireDemoRepositoryImpl : QuestionnaireDemoRepository {

    override fun getQuizItems(): List<QuestionType.Quiz> {
        return listOf(
            QuestionType.Quiz(
                id = "1", questionText = "", answers = listOf(
                    "", "", ""
                )
            )
        )
    }

    override fun getGapSubstitutionItems(): List<QuestionType.GapSubstitution> {
        return listOf(
            QuestionType.GapSubstitution(
                id = "1", questionText = "", textWithPlaceholders = "", placeholderItems = listOf(
                    "", "", ""
                )
            )
        )
    }

    override fun getMatchingItems(): List<QuestionType.Matching> {
        return listOf(
            QuestionType.Matching(
                id = "1", questionText = "", leftItems = listOf(), rightItems = listOf()
            )
        )
    }

    override fun getRatingItems(): List<QuestionType.Rating> {
        return listOf(
            QuestionType.Rating(
                id = "1", questionText = "", maxValue = 5
            )
        )
    }
}
