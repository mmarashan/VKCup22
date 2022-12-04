package io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.model

import io.volgadev.sampleapp.feature.dzentopicspicker.domain.model.Topic

internal sealed class DzenTopicsPickerScreenEvent {

    class ItemClicked(val item: Topic) : DzenTopicsPickerScreenEvent()
}