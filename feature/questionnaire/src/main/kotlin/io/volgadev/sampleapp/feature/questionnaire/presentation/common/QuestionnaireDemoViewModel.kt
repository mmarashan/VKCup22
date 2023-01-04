package io.volgadev.sampleapp.feature.questionnaire.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal open class QuestionnaireDemoViewModel<out T : QuestionType>(
    private val questions: List<T>
) : ViewModel() {

    private var currentItemIndex = 0

    private val _currentItem = MutableStateFlow<T?>(null)
    open val currentItem: StateFlow<T?> = _currentItem

    init {
        assert(questions.isNotEmpty())
        onStart()
    }

    fun onClickNext() {
        if (currentItemIndex == questions.size - 1) {
            currentItemIndex = 0
        } else {
            currentItemIndex++
        }
        val item = questions[currentItemIndex]
        _currentItem.tryEmit(item)
    }

    private fun onStart() {
        viewModelScope.launch {
            val item = questions.first()
            _currentItem.emit(item)
        }
    }
}