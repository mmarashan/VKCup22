package io.volgadev.sampleapp.feature.questionnaire.data

import io.volgadev.sampleapp.feature.questionnaire.domain.QuestionnaireDemoRepository
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuizAnswer

internal class QuestionnaireDemoRepositoryImpl : QuestionnaireDemoRepository {

    override fun getQuizItems(): List<QuestionType.Quiz> {
        return listOf(
            QuestionType.Quiz(
                id = "1",
                questionText = "В каком году был ЧМ по футболу в России?",
                answers = listOf(
                    QuizAnswer("1", "2008", 1),
                    QuizAnswer("2", "2014", 2),
                    QuizAnswer("3", "2018", 3),
                    QuizAnswer("4", "Такого не было", 0),
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
