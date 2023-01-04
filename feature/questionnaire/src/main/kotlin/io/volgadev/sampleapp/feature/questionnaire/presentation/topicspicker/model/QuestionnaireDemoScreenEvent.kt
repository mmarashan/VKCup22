package io.volgadev.sampleapp.feature.questionnaire.presentation.topicspicker.model

internal sealed class QuestionnaireDemoScreenEvent {

    class ItemClicked(val item: Topic) : QuestionnaireDemoScreenEvent()
}