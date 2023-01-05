package io.volgadev.sampleapp.feature.questionnaire.domain.model

internal data class QuizAnswer(
    val id: String, val text: String, val votesCount: Int
)