package io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling

import io.volgadev.sampleapp.feature.questionnaire.domain.QuestionnaireDemoRepository
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType
import io.volgadev.sampleapp.feature.questionnaire.presentation.common.QuestionnaireDemoViewModel
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.model.GapFillingItemState
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.model.GapFillingTextItem
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.model.GapsCheckResult
import kotlinx.coroutines.flow.MutableStateFlow

internal class GapFillingViewModel(
    repository: QuestionnaireDemoRepository,
) : QuestionnaireDemoViewModel<QuestionType.GapFilling>(repository.getGapFillingItems()) {

    val currentViewState = MutableStateFlow<GapFillingItemState>(emptyState())

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
        if (items[index] is GapFillingTextItem.Gap) {
            items[index] = GapFillingTextItem.Gap(newValue)
        }
        currentViewState.tryEmit(state.copy(items = items))
    }

    fun onClickCheck() {
        val gapsCheckingResults = mutableMapOf<Int, Boolean>()
        val question = currentItem.value as QuestionType.GapFilling
        val state = currentViewState.value
        val items = state.items
        var answersCounter = 0
        items.forEachIndexed Loop@{ i, item ->
            if (item is GapFillingTextItem.Gap) {
                val answer = question.answers.getOrNull(answersCounter) ?: return@Loop
                gapsCheckingResults[i] = answer == item.value
                answersCounter++
            }
        }
        currentViewState.tryEmit(state.copy(gapsCheckResult = GapsCheckResult(gapsCheckingResults)))
    }

    private fun restartViewState() {
        val question = currentItem.value as QuestionType.GapFilling
        val viewState = mapQuestionToViewState(question)
        currentViewState.tryEmit(viewState)
    }

    private fun mapQuestionToViewState(question: QuestionType.GapFilling): GapFillingItemState {
        return GapFillingItemState(id = question.id,
            questionText = question.questionText,
            items = question.textWithPlaceholders.split(" ").map { item ->
                if (item == question.placeholder) {
                    GapFillingTextItem.Gap()
                } else {
                    GapFillingTextItem.Word(value = item)
                }
            })
    }

    private fun emptyState() = GapFillingItemState(
        id = "", questionText = "", items = listOf()
    )
}