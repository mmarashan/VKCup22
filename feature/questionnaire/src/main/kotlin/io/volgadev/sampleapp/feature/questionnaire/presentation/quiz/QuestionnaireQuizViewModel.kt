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
        if (currentAnswerId.value != null) return

        updateSelectedQuizAnswer(answerId)
        currentAnswerId.tryEmit(answerId)
    }

    override fun onClickNext() {
        currentAnswerId.tryEmit(null)
        super.onClickNext()
    }

    private fun updateSelectedQuizAnswer(answerId: String) {
        val currentQuiz = currentItem.value ?: return
        val answers = currentQuiz.answers
        val answer = currentQuiz.answers.firstOrNull { it.id == answerId } ?: return
        val answerWithNewVote = answer.copy(votesCount = answer.votesCount + 1)
        val newAnswers = answers.map { if (it.id == answerId) answerWithNewVote else it }
        updateCurrentItem(
            currentQuiz.copy(
                answers = newAnswers
            )
        )
    }
}