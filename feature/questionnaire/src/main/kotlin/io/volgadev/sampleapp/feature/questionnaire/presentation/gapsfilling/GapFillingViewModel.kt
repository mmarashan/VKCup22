package io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling

import io.volgadev.sampleapp.feature.questionnaire.domain.QuestionnaireDemoRepository
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType
import io.volgadev.sampleapp.feature.questionnaire.presentation.common.QuestionnaireDemoViewModel
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.model.GapFillingItemState
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.model.GapFillingTextItem
import kotlinx.coroutines.flow.MutableStateFlow

internal class GapFillingViewModel(
    repository: QuestionnaireDemoRepository,
) : QuestionnaireDemoViewModel<QuestionType.GapFilling>(repository.getGapFillingItems()) {

    val currentViewState = MutableStateFlow<GapFillingItemState?>(null)

    init {
        restartViewState()
    }

    override fun onClickNext() {
        super.onClickNext()
        restartViewState()
    }

    fun onGapInputChange(index: Int, newValue: String) {

    }

    fun onClickCheck() {

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
            }
        )
    }
}