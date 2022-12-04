package io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.model

import io.volgadev.sampleapp.feature.dzentopicspicker.domain.model.Topic

internal sealed class DzenTopicsPickerScreenState {

    data class Content(
        val items: Map<Topic, Boolean>
    ) : DzenTopicsPickerScreenState()

    object Error : DzenTopicsPickerScreenState()

    object Loading : DzenTopicsPickerScreenState()
}