package io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.model.GapFillingItemState
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.model.GapFillingTextItem
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.model.GapsCheckResult
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.ui.GapFillingComponent
import org.koin.androidx.compose.getViewModel

@Composable
internal fun GapFillingScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: GapFillingViewModel = getViewModel()
) {
    val currentItem = viewModel.currentViewState.collectAsState(initial = null)

    val itemValue = currentItem.value
    if (itemValue != null) {
        GapFillingScreenContent(
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
private fun GapFillingScreenContent(
    modifier: Modifier,
    item: GapFillingItemState,
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
                GapFillingComponent(
                    modifier = Modifier.padding(16.dp),
                    state = item,
                    onChangeGapValue = onChangeGapValue
                )
                MainScreenButton(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(R.string.to_check),
                    backgroundColor = AppColors.primaryOrange,
                    onClick = onClickCheck
                )
            }
        })
}

@Preview(showBackground = true)
@Composable
internal fun GapFillingScreenPreview() {
    GapFillingScreenContent(modifier = Modifier,
        item = GapFillingItemState(
            id = "1",
            questionText = "Заполните пропуски в тексте",
            items = listOf(
                GapFillingTextItem.Word("У"),
                GapFillingTextItem.Word("лукоморья"),
                GapFillingTextItem.Gap("дуб"),
                GapFillingTextItem.Word("зеленый."),
                GapFillingTextItem.Word("Злотая"),
                GapFillingTextItem.Gap("язь"),
                GapFillingTextItem.Word("на"),
                GapFillingTextItem.Gap("дубе"),
                GapFillingTextItem.Word("том."),
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