package io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging

import io.volgadev.sampleapp.feature.questionnaire.domain.QuestionnaireDemoRepository
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType
import io.volgadev.sampleapp.feature.questionnaire.presentation.common.QuestionnaireDemoViewModel
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapDraggingItemState
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapDraggingTextItem
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapsDraggingCheckResult
import kotlinx.coroutines.flow.MutableStateFlow

internal class GapDraggingViewModel(
    repository: QuestionnaireDemoRepository,
) : QuestionnaireDemoViewModel<QuestionType.GapFilling>(repository.getGapFillingItems()) {

    val currentViewState = MutableStateFlow(emptyState())

    init {
        restartViewState()
    }

    override fun onClickNext() {
        super.onClickNext()
        restartViewState()
    }

    fun onGapInputChange(index: Int, newValue: String?) {
        val state = currentViewState.value
        val items = state.items.toMutableList()
        val textItem = items[index]
        if (textItem !is GapDraggingTextItem.Gap) return
        if (textItem.value == newValue) return

        val isClearing = newValue == null
        val oldValue: String? = textItem.value
        items[index] = GapDraggingTextItem.Gap(newValue)

        val isCheckEnabled = items.filterIsInstance<GapDraggingTextItem.Gap>()
            .all { it.value.orEmpty().isNotEmpty() }

        var oneItemWasRemoved = false
        val newTipsList: List<String> = if (!isClearing) {
            state.tips.toMutableList().filter {
                if (it == newValue && !oneItemWasRemoved) {
                    oneItemWasRemoved = true
                    false
                } else {
                    true
                }
            }.let {
                if (oldValue != null) it.plus(oldValue) else it
            }
        } else {
            state.tips.plus(oldValue.orEmpty())
        }
        currentViewState.tryEmit(
            state.copy(
                items = items,
                isCheckEnabled = isCheckEnabled,
                tips = newTipsList
            )
        )
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
                gapsCheckResult = GapsDraggingCheckResult(gapsCheckingResults),
                isNextEnabled = true
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
            tips = question.answers,
            items = question.textWithPlaceholders.split(" ").map { item ->
                if (item == question.placeholder) {
                    GapDraggingTextItem.Gap()
                } else {
                    GapDraggingTextItem.Word(value = item)
                }
            }
        )
    }

    private fun emptyState() = GapDraggingItemState(
        id = "",
        questionText = "",
        items = listOf(),
        tips = listOf()
    )
}
