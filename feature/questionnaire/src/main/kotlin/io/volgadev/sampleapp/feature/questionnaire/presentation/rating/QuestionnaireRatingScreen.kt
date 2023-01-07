package io.volgadev.sampleapp.feature.questionnaire.presentation.rating

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import io.volgadev.core.uikit.composable.ratingbar.RatingBar
import io.volgadev.core.uikit.composable.ratingbar.RatingBarConfig
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType
import io.volgadev.sampleapp.feature.questionnaire.presentation.common.QuestionnaireDemoScreen
import org.koin.androidx.compose.getViewModel

@Composable
internal fun QuestionnaireRatingScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: QuestionnaireRatingViewModel = getViewModel()
) {
    val selectedRating = viewModel.selectedRating.collectAsState(initial = null)
    val currentItem = viewModel.currentItem.collectAsState(initial = null)

    val itemValue = currentItem.value
    if (itemValue != null) {
        QuestionnaireRatingScreenContent(
            modifier = modifier,
            item = itemValue,
            selectedRating = selectedRating.value,
            onClickNext = viewModel::onClickNext,
            onClickBack = navController::popBackStack,
            onSetsRating = viewModel::onSetsRating
        )
    }
}

@Composable
internal fun QuestionnaireRatingScreenContent(
    modifier: Modifier,
    item: QuestionType.Rating,
    selectedRating: Int?,
    onClickBack: () -> Unit,
    onClickNext: () -> Unit,
    onSetsRating: (Int) -> Unit
) {
    QuestionnaireDemoScreen(modifier = modifier,
        onClickBack = onClickBack,
        onClickNext = onClickNext,
        isNextEnabled = selectedRating != null,
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    RatingBar(
                        modifier = Modifier.padding(16.dp),
                        value = selectedRating ?: 0,
                        config = RatingBarConfig()
                            .size(54.dp)
                            .numStars(item.maxValue),
                        onValueChange = onSetsRating
                    ) {}
                }
            }
        })
}

@Preview(showBackground = true)
@Composable
internal fun QuestionnaireRatingScreenPreview() {
    QuestionnaireRatingScreenContent(modifier = Modifier,
        item = QuestionType.Rating(
            id = "", questionText = "Как вам этот пост про чемпионат мира?", maxValue = 5
        ),
        selectedRating = 3,
        onClickNext = {},
        onClickBack = {},
        onSetsRating = {}
    )
}