package io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
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
                modifier = modifier,
                items = stateValue.items,
                onClickItem = {}
            )
        }
        DzenTopicsPickerScreenState.Error -> {
        }
        DzenTopicsPickerScreenState.Loading -> {
        }
    }
}