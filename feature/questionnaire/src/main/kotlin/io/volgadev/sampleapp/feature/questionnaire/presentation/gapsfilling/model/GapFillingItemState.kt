package io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.model

internal data class GapFillingItemState(
    val id: String,
    val questionText: String,
    val items: List<GapFillingTextItem>,
    val isNextEnabled: Boolean = false,
    val correctGapsIndexes: List<Int> = listOf(),
    val incorrectGapsIndexes: List<Int> = listOf()
)

internal sealed class GapFillingTextItem {
    internal class Word(val value: String) : GapFillingTextItem()
    internal class Gap(val value: String = "") : GapFillingTextItem()
}
