package io.volgadev.sampleapp.feature.questionnaire.presentation.pairmapping.model

internal data class PairMappingItemState(
    val id: String,
    val questionText: String,
    val pairs: Map<String, PairItemAnswer>,
    val freeItems: List<String>,
    val isNextEnabled: Boolean = false,
    val isCheckEnabled: Boolean = false,
)

internal data class PairItemAnswer(
    val value: String? = null,
    val checkResult: Boolean? = null
)