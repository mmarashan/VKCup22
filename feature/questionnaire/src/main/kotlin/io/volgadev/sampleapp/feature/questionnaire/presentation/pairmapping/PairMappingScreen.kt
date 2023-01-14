package io.volgadev.sampleapp.feature.questionnaire.presentation.pairmapping

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
import io.volgadev.sampleapp.feature.questionnaire.presentation.pairmapping.model.PairItemAnswer
import io.volgadev.sampleapp.feature.questionnaire.presentation.pairmapping.model.PairMappingItemState
import io.volgadev.sampleapp.feature.questionnaire.presentation.pairmapping.ui.PairMappingComponent
import org.koin.androidx.compose.getViewModel

@Composable
internal fun PairMappingScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: PairMappingViewModel = getViewModel()
) {
    val currentItem = viewModel.currentViewState.collectAsState(initial = null)

    val itemValue = currentItem.value
    if (itemValue != null) {
        PairMappingScreenContent(
            modifier = modifier,
            item = itemValue,
            onClickBack = navController::popBackStack,
            onClickNext = viewModel::onClickNext,
            onChangePairValue = viewModel::onChangePairValue,
            onClickCheck = viewModel::onClickCheck
        )
    }
}

@Composable
private fun PairMappingScreenContent(
    modifier: Modifier,
    item: PairMappingItemState,
    onClickBack: () -> Unit,
    onClickNext: () -> Unit,
    onClickCheck: () -> Unit,
    onChangePairValue: (String, String?) -> Unit
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
                PairMappingComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    state = item,
                    onChangePairValue = onChangePairValue
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
        }
    )
}

@Preview(showBackground = true)
@Composable
internal fun PairMappingScreenPreview() {
    PairMappingScreenContent(modifier = Modifier,
        item = PairMappingItemState(
            id = "1",
            questionText = "Найдите соответствие",
            pairs = mapOf(
                "Аргентина" to PairItemAnswer(value = "Буэнос-Айрос"),
                "Португалия" to PairItemAnswer(),
                "Бразилия" to PairItemAnswer(),
                "Россия " to PairItemAnswer()
            ),
            freeItems = listOf("Лиссабон", "Рио-Де-Жанейро", "Москва"),
        ),
        onClickBack = {},
        onClickNext = {},
        onChangePairValue = { _, _ -> },
        onClickCheck = {}
    )
}