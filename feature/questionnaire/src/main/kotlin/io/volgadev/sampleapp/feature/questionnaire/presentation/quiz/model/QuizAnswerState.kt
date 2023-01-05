package io.volgadev.sampleapp.feature.questionnaire.presentation.quiz.model

import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType

internal data class QuizAnswerState(
    val quiz: QuestionType.Quiz,
    val answerId: String? = null
)