package io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.volgadev.sampleapp.feature.questionnaire.presentation.common.QuestionnaireDemoScreen
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.model.GapFillingItemState
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
    QuestionnaireDemoScreen(modifier = modifier,
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

            }
        })
}

@Preview(showBackground = true)
@Composable
internal fun GapFillingScreenPreview() {
    GapFillingScreenContent(modifier = Modifier,
        item = GapFillingItemState(id = "", "Заполните пропуски:", items = listOf()),
        onClickBack = {},
        onClickNext = {},
        onChangeGapValue = { _, _ -> },
        onClickCheck = {}
    )
}