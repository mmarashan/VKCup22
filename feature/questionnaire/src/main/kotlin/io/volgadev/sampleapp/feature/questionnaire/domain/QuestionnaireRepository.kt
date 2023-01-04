package io.volgadev.sampleapp.feature.questionnaire.domain

import io.volgadev.core.result.Result
import io.volgadev.sampleapp.feature.questionnaire.domain.model.Topic

internal interface QuestionnaireRepository {
    suspend fun loadTopics(): Result<List<Topic>, Throwable>
}
