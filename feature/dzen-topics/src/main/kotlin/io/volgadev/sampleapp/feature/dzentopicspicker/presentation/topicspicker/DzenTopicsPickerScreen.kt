package io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.model.DzenTopicsPickerScreenEvent
import io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.model.DzenTopicsPickerScreenState
import io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.ui.DzenTopicsPickerScreenContent
import org.koin.androidx.compose.getViewModel


@Composable
internal fun DzenTopicsPickerScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: DzenTopicsPickerViewModel = getViewModel()
) {
    val state = viewModel.state.collectAsState(initial = DzenTopicsPickerScreenState.Loading)

    when (val stateValue = state.value) {
        is DzenTopicsPickerScreenState.Content -> {
            DzenTopicsPickerScreenContent(
                modifier = modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp),
                items = stateValue.items,
                onClickItem = {
                    viewModel.onEvent(DzenTopicsPickerScreenEvent.ItemClicked(it))
                }
            )
        }
        DzenTopicsPickerScreenState.Error -> {
        }
        DzenTopicsPickerScreenState.Loading -> {
        }
    }
}