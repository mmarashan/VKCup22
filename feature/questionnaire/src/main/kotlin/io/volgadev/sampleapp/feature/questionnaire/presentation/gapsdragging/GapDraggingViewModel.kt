package io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging

import io.volgadev.sampleapp.feature.questionnaire.domain.QuestionnaireDemoRepository
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType
import io.volgadev.sampleapp.feature.questionnaire.presentation.common.QuestionnaireDemoViewModel
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapDraggingItemState
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapDraggingTextItem
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapsCheckResult
import kotlinx.coroutines.flow.MutableStateFlow

internal class GapDraggingViewModel(
    repository: QuestionnaireDemoRepository,
) : QuestionnaireDemoViewModel<QuestionType.GapFilling>(repository.getGapFillingItems()) {

    val currentViewState = MutableStateFlow<GapDraggingItemState>(emptyState())

    init {
        restartViewState()
    }

    override fun onClickNext() {
        super.onClickNext()
        restartViewState()
    }

    fun onGapInputChange(index: Int, newValue: String) {
        val state = currentViewState.value
        val items = state.items.toMutableList()
        if (items[index] is GapDraggingTextItem.Gap) {
            items[index] = GapDraggingTextItem.Gap(newValue)
        }
        val isCheckEnabled = items.filterIsInstance<GapDraggingTextItem.Gap>()
            .all { (it as GapDraggingTextItem.Gap).value.isNotEmpty() }
        currentViewState.tryEmit(state.copy(items = items, isCheckEnabled = isCheckEnabled))
    }

    fun onClickCheck() {
        val gapsCheckingResults = mutableMapOf<Int, Boolean>()
        val question = currentItem.value as QuestionType.GapFilling
        val state = currentViewState.value
        val items = state.items
        var answersCounter = 0
        items.forEachIndexed Loop@{ i, item ->
            if (item is GapDraggingTextItem.Gap) {
                val answer = question.answers.getOrNull(answersCounter) ?: return@Loop
                gapsCheckingResults[i] = answer == item.value
                answersCounter++
            }
        }
        currentViewState.tryEmit(
            state.copy(
                gapsCheckResult = GapsCheckResult(gapsCheckingResults), isNextEnabled = true
            )
        )
    }

    private fun restartViewState() {
        val question = currentItem.value as QuestionType.GapFilling
        val viewState = mapQuestionToViewState(question)
        currentViewState.tryEmit(viewState)
    }

    private fun mapQuestionToViewState(question: QuestionType.GapFilling): GapDraggingItemState {
        return GapDraggingItemState(
            id = question.id,
            questionText = question.questionText,
            items = question.textWithPlaceholders.split(" ").map { item ->
                if (item == question.placeholder) {
                    GapDraggingTextItem.Gap()
                } else {
                    GapDraggingTextItem.Word(value = item)
                }
            })
    }

    private fun emptyState() = GapDraggingItemState(
        id = "", questionText = "", items = listOf()
    )
}