package io.volgadev.sampleapp.feature.questionnaire.presentation.topicspicker

import androidx.lifecycle.viewModelScope
import io.volgadev.core.architecture.SEAViewModel
import io.volgadev.core.result.Result
import io.volgadev.sampleapp.feature.questionnaire.domain.QuestionnaireRepository
import io.volgadev.sampleapp.feature.questionnaire.domain.model.Topic
import io.volgadev.sampleapp.feature.questionnaire.presentation.topicspicker.model.QuestionnaireDemoScreenAction
import io.volgadev.sampleapp.feature.questionnaire.presentation.topicspicker.model.QuestionnaireDemoScreenEvent
import io.volgadev.sampleapp.feature.questionnaire.presentation.topicspicker.model.QuestionnaireDemoScreenState
import kotlinx.coroutines.launch

internal class QuestionnaireViewModel(
    private val questionnaireRepository: QuestionnaireRepository
) : SEAViewModel<QuestionnaireDemoScreenState, QuestionnaireDemoScreenEvent, QuestionnaireDemoScreenAction>() {

    init {
        onStart()
    }

    override fun onEvent(event: QuestionnaireDemoScreenEvent) {
        when (event) {
            is QuestionnaireDemoScreenEvent.ItemClicked -> onItemClicked(event.item)
        }
    }

    private fun onStart() {
        viewModelScope.launch {
            when (val assetsRequestResult = questionnaireRepository.loadTopics()) {
                is Result.Error -> {
                    setState(QuestionnaireDemoScreenState.Error)
                }
                is Result.Success -> {
                    setState(
                        QuestionnaireDemoScreenState.Content(
                            items = itemsToInitialMap(assetsRequestResult.value)
                        )
                    )
                }
            }
        }
    }

    private fun itemsToInitialMap(items: List<Topic>): Map<Topic, Boolean> {
        return items.associateWith { false }
    }

    private fun onItemClicked(item: Topic) {
        val state = getStateValueOrNull() as? QuestionnaireDemoScreenState.Content ?: return

        val items = state.items.toMutableMap()
        val currentPickerValue: Boolean = items[item] ?: false
        items[item] = !currentPickerValue
        setState(
            QuestionnaireDemoScreenState.Content(
                items = items
            )
        )
    }
}