package io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model

internal data class GapDraggingItemState(
    val id: String,
    val questionText: String,
    val items: List<GapDraggingTextItem>,
    val isNextEnabled: Boolean = false,
    val isCheckEnabled: Boolean = false,
    val gapsCheckResult: GapsCheckResult? = null
)

internal sealed class GapDraggingTextItem {
    internal class Word(val value: String) : GapDraggingTextItem()
    internal class Gap(val value: String = "") : GapDraggingTextItem()
}

/**
 * Результат проверки вставленных пропусков
 * Ключ - индекс пропуска в списке [GapDraggingItemState.items]
 */
internal data class GapsCheckResult(
    val gapsCheckingResults: Map<Int, Boolean> = mapOf(),
)
