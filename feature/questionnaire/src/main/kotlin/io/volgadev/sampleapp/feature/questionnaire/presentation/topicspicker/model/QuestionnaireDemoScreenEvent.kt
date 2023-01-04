package io.volgadev.sampleapp.feature.questionnaire.presentation.topicspicker.model

import io.volgadev.sampleapp.feature.questionnaire.domain.model.Topic

internal sealed class QuestionnaireDemoScreenEvent {

    class ItemClicked(val item: Topic) : QuestionnaireDemoScreenEvent()
}