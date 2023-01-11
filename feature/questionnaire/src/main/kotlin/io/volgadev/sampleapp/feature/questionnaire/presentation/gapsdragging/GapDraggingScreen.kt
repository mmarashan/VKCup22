package io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.volgadev.core.uikit.composable.button.MainScreenButton
import io.volgadev.core.uikit.theme.AppColors
import io.volgadev.sampleapp.feature.questionnaire.R
import io.volgadev.sampleapp.feature.questionnaire.presentation.common.QuestionnaireDemoScreen
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapDraggingItemState
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapDraggingTextItem
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapsCheckResult
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.ui.GapDraggingComponent
import org.koin.androidx.compose.getViewModel

@Composable
internal fun GapDraggingScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: GapDraggingViewModel = getViewModel()
) {
    val currentItem = viewModel.currentViewState.collectAsState(initial = null)

    val itemValue = currentItem.value
    if (itemValue != null) {
        GapDraggingScreenContent(
            modifier = modifier,
            item = itemValue,
            onClickBack = navController::popBackStack,
            onClickNext = viewModel::onClickNext,
            onChangeGapValue = viewModel::onGapInputChange,
            onClickCheck = viewModel::onClickCheck
        )
    }
}

@Composable
private fun GapDraggingScreenContent(
    modifier: Modifier,
    item: GapDraggingItemState,
    onClickBack: () -> Unit,
    onClickNext: () -> Unit,
    onClickCheck: () -> Unit,
    onChangeGapValue: (Int, String) -> Unit
) {
    QuestionnaireDemoScreen(
        modifier = modifier,
        onClickBack = onClickBack,
        onClickNext = onClickNext,
        isNextEnabled = item.isNextEnabled,
        content = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    fontSize = 24.sp,
                    lineHeight = 28.sp,
                    text = item.questionText
                )
                GapDraggingComponent(
                    modifier = Modifier.padding(16.dp),
                    state = item,
                    onChangeGapValue = onChangeGapValue
                )
                Spacer(modifier = Modifier.height(16.dp))
                MainScreenButton(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(R.string.to_check),
                    backgroundColor = AppColors.primaryOrange,
                    enabled = item.isCheckEnabled,
                    onClick = onClickCheck
                )
            }
        })
}

@Preview(showBackground = true)
@Composable
internal fun GapDraggingScreenPreview() {
    GapDraggingScreenContent(modifier = Modifier,
        item = GapDraggingItemState(
            id = "1",
            questionText = "Заполните пропуски в тексте",
            items = listOf(
                GapDraggingTextItem.Word("У"),
                GapDraggingTextItem.Word("лукоморья"),
                GapDraggingTextItem.Gap("дуб"),
                GapDraggingTextItem.Word("зеленый."),
                GapDraggingTextItem.Word("Злотая"),
                GapDraggingTextItem.Gap("язь"),
                GapDraggingTextItem.Word("на"),
                GapDraggingTextItem.Gap("дубе"),
                GapDraggingTextItem.Word("том."),
            ),
            gapsCheckResult = GapsCheckResult(
                gapsCheckingResults = mapOf(2 to true, 5 to false, 7 to true)
            )
        ),
        onClickBack = {},
        onClickNext = {},
        onChangeGapValue = { _, _ -> },
        onClickCheck = {}
    )
}