package io.volgadev.sampleapp.feature.questionnaire.presentation.quiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.volgadev.core.uikit.composable.button.MainScreenButton
import io.volgadev.core.uikit.theme.AppColors
import io.volgadev.core.uikit.theme.AppTheme
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuizAnswer
import io.volgadev.sampleapp.feature.questionnaire.presentation.quiz.ui.QuizComponent
import org.koin.androidx.compose.getViewModel


@Composable
internal fun QuestionnaireQuizScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: QuestionnaireQuizViewModel = getViewModel()
) {
    val currentItem = viewModel.currentItem.collectAsState(initial = null)
    val currentAnswerId = viewModel.currentAnswerId.collectAsState(initial = null)

    val itemValue = currentItem.value

    if (itemValue != null) {
        QuestionnaireQuizScreen(
            modifier = modifier,
            quiz = itemValue,
            selectedId = currentAnswerId.value,
            onClickAnswer = {
                viewModel.onSelectAnswer(it)
            }
        )
    }
}

@Composable
internal fun QuestionnaireQuizScreen(
    modifier: Modifier,
    quiz: QuestionType.Quiz,
    selectedId: String?,
    onClickAnswer: (String) -> Unit
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar {

            }
        },
        floatingActionButton = {
            MainScreenButton(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { },
                text = "Следующий",
                backgroundColor = AppColors.darkBackground
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        QuizComponent(
            modifier = Modifier.fillMaxSize(),
            quiz = quiz,
            selectedId = selectedId,
            onClickAnswer = onClickAnswer
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun QuestionnaireQuizScreenPreview() {
    AppTheme {
        QuestionnaireQuizScreen(modifier = Modifier.fillMaxSize(), quiz = QuestionType.Quiz(
            id = "1",
            questionText = "В каком году был ЧМ по футболу в России?",
            answers = listOf(
                QuizAnswer("1", "2008", 1),
                QuizAnswer("2", "2014", 2),
                QuizAnswer("3", "2018", 3),
                QuizAnswer("4", "Такого не было", 0),
            )
        ), selectedId = "3", onClickAnswer = {})
    }
}