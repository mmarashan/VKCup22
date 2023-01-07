package io.volgadev.sampleapp.feature.questionnaire.presentation.rating

import io.volgadev.sampleapp.feature.questionnaire.domain.QuestionnaireDemoRepository
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType
import io.volgadev.sampleapp.feature.questionnaire.presentation.common.QuestionnaireDemoViewModel
import kotlinx.coroutines.flow.MutableStateFlow

internal class QuestionnaireRatingViewModel(
    repository: QuestionnaireDemoRepository
) : QuestionnaireDemoViewModel<QuestionType.Quiz>(repository.getQuizItems()) {

    val selectedRating = MutableStateFlow<Int?>(null)

    fun onSetsRating(value: Int) {

    }

    override fun onClickNext() {
        selectedRating.tryEmit(null)
        super.onClickNext()
    }
}