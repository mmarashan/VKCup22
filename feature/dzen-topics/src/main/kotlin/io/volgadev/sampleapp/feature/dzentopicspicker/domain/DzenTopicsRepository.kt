package io.volgadev.sampleapp.feature.dzentopicspicker.domain

import io.volgadev.core.result.Result
import io.volgadev.sampleapp.feature.dzentopicspicker.domain.model.Topic

internal interface DzenTopicsRepository {
    suspend fun loadTopics(): Result<List<Topic>, Throwable>
}
