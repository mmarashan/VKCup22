package io.volgadev.sampleapp.feature.questionnaire.presentation.pairmapping

import io.volgadev.sampleapp.feature.questionnaire.domain.QuestionnaireDemoRepository
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType
import io.volgadev.sampleapp.feature.questionnaire.presentation.common.QuestionnaireDemoViewModel
import io.volgadev.sampleapp.feature.questionnaire.presentation.pairmapping.model.PairItemAnswer
import io.volgadev.sampleapp.feature.questionnaire.presentation.pairmapping.model.PairMappingItemState
import kotlinx.coroutines.flow.MutableStateFlow

internal class PairMappingViewModel(
    repository: QuestionnaireDemoRepository,
) : QuestionnaireDemoViewModel<QuestionType.PairMapping>(repository.getPairMappingItems()) {

    val currentViewState = MutableStateFlow(emptyState())

    init {
        restartViewState()
    }

    override fun onClickNext() {
        super.onClickNext()
        restartViewState()
    }

    fun onGapInputChange(key: String, newValue: String?) {
        val state = currentViewState.value
        val oldValue = state.pairs.getValue(key)
        if (oldValue.value == newValue) return

        val newPairs = state.pairs.toMutableMap().apply { put(key, PairItemAnswer(newValue)) }
        var oneItemWasRemoved = false
        val newFreeItems: List<String> = if (newValue != null) {
            state.freeItems.toMutableList().filter {
                if (it == newValue && !oneItemWasRemoved) {
                    oneItemWasRemoved = true
                    false
                } else {
                    true
                }
            }
        } else {
            state.freeItems.plus(oldValue.value.orEmpty())
        }
        val isCheckEnabled = newPairs.values.all { it.value != null }
        val newState = state.copy(
            pairs = newPairs,
            freeItems = newFreeItems,
            isCheckEnabled = isCheckEnabled
        )
        currentViewState.tryEmit(newState)
    }

    fun onClickCheck() {
        val question = currentItem.value as QuestionType.PairMapping
        val state = currentViewState.value
        if (state.freeItems.isNotEmpty()) throw IllegalStateException()

        val newPairs = state.pairs.keys.associateWith {
            val value = state.pairs.getValue(it).value
            PairItemAnswer(
                value = value,
                checkResult = value == question.itemsMap[it]
            )
        }
        currentViewState.tryEmit(
            state.copy(
                pairs = newPairs
            )
        )
    }

    private fun restartViewState() {
        val question = currentItem.value as QuestionType.PairMapping
        val viewState = mapQuestionToViewState(question)
        currentViewState.tryEmit(viewState)
    }

    private fun mapQuestionToViewState(question: QuestionType.PairMapping): PairMappingItemState {
        return PairMappingItemState(
            id = question.id,
            questionText = question.questionText,
            pairs = question.itemsMap.keys.associateWith { PairItemAnswer() },
            freeItems = question.itemsMap.values.toList()
        )
    }

    private fun emptyState() = PairMappingItemState(
        id = "",
        questionText = "",
        freeItems = listOf(),
        pairs = mapOf()
    )
}
