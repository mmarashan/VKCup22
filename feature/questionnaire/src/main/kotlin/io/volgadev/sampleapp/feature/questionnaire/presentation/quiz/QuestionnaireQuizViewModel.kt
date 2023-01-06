package io.volgadev.sampleapp.feature.questionnaire.presentation.quiz

import io.volgadev.sampleapp.feature.questionnaire.domain.QuestionnaireDemoRepository
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType
import io.volgadev.sampleapp.feature.questionnaire.presentation.common.QuestionnaireDemoViewModel
import kotlinx.coroutines.flow.MutableStateFlow

internal class QuestionnaireQuizViewModel(
    repository: QuestionnaireDemoRepository
) : QuestionnaireDemoViewModel<QuestionType.Quiz>(repository.getQuizItems()) {

    val currentAnswerId = MutableStateFlow<String?>(null)

    fun onSelectAnswer(answerId: String) {
        val currentQuiz = currentItem.value ?: return
        currentAnswerId.tryEmit(answerId)
    }

    override fun onClickNext() {
        currentAnswerId.tryEmit(null)
        super.onClickNext()
    }
}