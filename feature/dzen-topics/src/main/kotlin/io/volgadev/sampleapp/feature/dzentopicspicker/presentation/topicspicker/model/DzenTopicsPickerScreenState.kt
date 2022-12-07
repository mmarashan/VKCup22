package io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.model

import io.volgadev.sampleapp.feature.dzentopicspicker.domain.model.Topic

internal sealed class DzenTopicsPickerScreenState {

    data class Content(
        val items: Map<Topic, Boolean>,
        val isNextButtonVisible: Boolean = items.values.contains(true),
        val isSkipButtonVisible: Boolean = items.values.all { !it },
    ) : DzenTopicsPickerScreenState()

    object Error : DzenTopicsPickerScreenState()

    object Loading : DzenTopicsPickerScreenState()
}