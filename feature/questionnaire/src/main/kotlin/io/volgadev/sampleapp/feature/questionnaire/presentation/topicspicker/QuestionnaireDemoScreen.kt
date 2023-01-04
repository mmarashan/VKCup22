package io.volgadev.sampleapp.feature.questionnaire.presentation.topicspicker

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.volgadev.sampleapp.feature.questionnaire.presentation.topicspicker.model.QuestionnaireDemoScreenEvent
import io.volgadev.sampleapp.feature.questionnaire.presentation.topicspicker.model.QuestionnaireDemoScreenState
import io.volgadev.sampleapp.feature.questionnaire.presentation.topicspicker.ui.DzenTopicsPickerScreenContent
import org.koin.androidx.compose.getViewModel


@Composable
internal fun QuestionnaireDemoScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: QuestionnaireDemoViewModel = getViewModel()
) {
    val state = viewModel.state.collectAsState(initial = QuestionnaireDemoScreenState.Loading)

    when (val stateValue = state.value) {
        is QuestionnaireDemoScreenState.Content -> {
            DzenTopicsPickerScreenContent(
                modifier = modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp),
                isSkipButtonVisible = stateValue.isSkipButtonVisible,
                isNextButtonVisible = stateValue.isNextButtonVisible,
                items = stateValue.items,
                onClickItem = {
                    viewModel.onEvent(QuestionnaireDemoScreenEvent.ItemClicked(it))
                },
                onClickSkip = {},
                onClickNext = {}
            )
        }
        QuestionnaireDemoScreenState.Error -> Unit
        QuestionnaireDemoScreenState.Loading -> Unit
    }
}