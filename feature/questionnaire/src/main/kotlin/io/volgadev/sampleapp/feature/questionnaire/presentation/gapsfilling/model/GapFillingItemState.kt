package io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.model

internal data class GapFillingItemState(
    val id: String,
    val questionText: String,
    val items: List<GapFillingTextItem>,
    val isNextEnabled: Boolean = false,
    val isCheckEnabled: Boolean = false,
    val gapsCheckResult: GapsCheckResult? = null
)

internal sealed class GapFillingTextItem {
    internal class Word(val value: String) : GapFillingTextItem()
    internal class Gap(val value: String = "") : GapFillingTextItem()
}

/**
 * Результат проверки вставленных пропусков
 * Ключ - индекс пропуска в списке [GapFillingItemState.items]
 */
internal data class GapsCheckResult(
    val gapsCheckingResults: Map<Int, Boolean> = mapOf(),
)
