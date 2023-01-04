package io.volgadev.sampleapp.feature.questionnaire.presentation.quiz

import io.volgadev.sampleapp.feature.questionnaire.domain.QuestionnaireDemoRepository
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType
import io.volgadev.sampleapp.feature.questionnaire.presentation.common.QuestionnaireDemoViewModel

internal class QuestionnaireQuizViewModel(
    repository: QuestionnaireDemoRepository
) : QuestionnaireDemoViewModel<QuestionType.Quiz>(repository.getQuizItems())