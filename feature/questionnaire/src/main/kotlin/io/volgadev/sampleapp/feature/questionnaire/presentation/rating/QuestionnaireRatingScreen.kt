package io.volgadev.sampleapp.feature.questionnaire.presentation.rating

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.volgadev.sampleapp.feature.questionnaire.presentation.common.QuestionnaireDemoScreen
import org.koin.androidx.compose.getViewModel

@Composable
internal fun QuestionnaireRatingScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: QuestionnaireRatingViewModel = getViewModel()
) {
    val selectedRating = viewModel.selectedRating.collectAsState(initial = null)

    QuestionnaireRatingScreenContent(
        modifier = modifier,
        selectedRating = selectedRating.value,
        onClickNext = viewModel::onClickNext,
        onClickBack = navController::popBackStack,
        onSetsRating = viewModel::onSetsRating
    )
}

@Composable
internal fun QuestionnaireRatingScreenContent(
    modifier: Modifier,
    selectedRating: Int?,
    onClickBack: () -> Unit,
    onClickNext: () -> Unit,
    onSetsRating: (Int) -> Unit
) {
    QuestionnaireDemoScreen(
        modifier = modifier,
        onClickBack = onClickBack,
        onClickNext = onClickNext,
        isNextEnabled = selectedRating != null,
        content = {

        }
    )
}

@Preview(showBackground = true)
@Composable
internal fun QuestionnaireRatingScreenPreview() {
    QuestionnaireRatingScreenContent(
        modifier = Modifier.padding(16.dp),
        selectedRating = 3,
        onClickNext = {},
        onClickBack = {},
        onSetsRating = {}
    )
}