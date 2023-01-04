package io.volgadev.sampleapp.feature.questionnaire.presentation.topicspicker.model

internal sealed class QuestionnaireDemoScreenState {

    data class Content(
        val items: Map<Topic, Boolean>,
        val isNextButtonVisible: Boolean = items.values.contains(true),
        val isSkipButtonVisible: Boolean = items.values.all { !it },
    ) : QuestionnaireDemoScreenState()

    object Error : QuestionnaireDemoScreenState()

    object Loading : QuestionnaireDemoScreenState()
}