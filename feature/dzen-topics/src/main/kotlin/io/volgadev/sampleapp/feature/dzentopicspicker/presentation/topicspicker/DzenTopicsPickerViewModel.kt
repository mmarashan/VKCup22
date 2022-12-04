package io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker

import androidx.lifecycle.viewModelScope
import io.volgadev.core.architecture.SEAViewModel
import io.volgadev.core.result.Result
import io.volgadev.sampleapp.feature.dzentopicspicker.domain.DzenTopicsRepository
import io.volgadev.sampleapp.feature.dzentopicspicker.domain.model.Topic
import io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.model.DzenTopicsPickerScreenAction
import io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.model.DzenTopicsPickerScreenEvent
import io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.model.DzenTopicsPickerScreenState
import kotlinx.coroutines.launch

internal class DzenTopicsPickerViewModel(
    private val dzenTopicsRepository: DzenTopicsRepository
) : SEAViewModel<DzenTopicsPickerScreenState, DzenTopicsPickerScreenEvent, DzenTopicsPickerScreenAction>() {

    init {
        onStart()
    }

    override fun onEvent(event: DzenTopicsPickerScreenEvent) {
        when (event) {
            is DzenTopicsPickerScreenEvent.ItemClicked -> onItemClicked(event.item)
        }
    }

    private fun onStart() {
        viewModelScope.launch {
            when (val assetsRequestResult = dzenTopicsRepository.loadTopics()) {
                is Result.Error -> {
                    setState(DzenTopicsPickerScreenState.Error)
                }
                is Result.Success -> {
                    setState(DzenTopicsPickerScreenState.Content(items = assetsRequestResult.value))
                }
            }
        }
    }

    private fun onItemClicked(item: Topic) {

    }
}