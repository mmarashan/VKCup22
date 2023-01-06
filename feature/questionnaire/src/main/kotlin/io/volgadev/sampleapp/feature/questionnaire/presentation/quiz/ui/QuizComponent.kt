package io.volgadev.sampleapp.feature.questionnaire.presentation.quiz.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.volgadev.core.uikit.theme.AppTheme
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuizAnswer
import kotlin.math.roundToInt

@Composable
internal fun QuizComponent(
    modifier: Modifier,
    quiz: QuestionType.Quiz,
    selectedId: String? = null,
    onClickAnswer: (String) -> Unit
) {
    val hasSelected = selectedId != null

    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp,
            lineHeight = 28.sp,
            text = quiz.questionText
        )
        quiz.answers.forEach { answer ->
            val isSelected = answer.id == selectedId
            QuizButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clickable {
                        onClickAnswer.invoke(answer.id)
                    }, text = answer.text, additionalText = if (hasSelected) {
                    if (quiz.allVotesCount > 0) {
                        (100f * answer.votesCount / quiz.allVotesCount).roundToInt()
                            .toString() + " %"
                    } else null
                } else null,
                state = if (isSelected) {
                    if (answer.isCorrect) {
                        QuizButtonState.CORRECT
                    } else {
                        QuizButtonState.INCORRECT
                    }
                } else {
                    QuizButtonState.UNSELECTED
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun QuizComponentPreview() {
    AppTheme {
        QuizComponent(modifier = Modifier.fillMaxSize(), quiz = QuestionType.Quiz(
            id = "1",
            questionText = "В каком году был ЧМ по футболу в России?",
            answers = listOf(
                QuizAnswer("1", "2008", 1),
                QuizAnswer("2", "2014", 2),
                QuizAnswer("3", "2018", 3),
                QuizAnswer("4", "Такого не было", 0),
            )
        ), selectedId = null, onClickAnswer = {})
    }
}

@Preview(showBackground = true)
@Composable
internal fun QuizComponentPreviewSelected() {
    AppTheme {
        QuizComponent(modifier = Modifier.fillMaxSize(), quiz = QuestionType.Quiz(
            id = "1",
            questionText = "В каком году был ЧМ по футболу в России?",
            answers = listOf(
                QuizAnswer("1", "2008", 1),
                QuizAnswer("2", "2014", 2),
                QuizAnswer("3", "2018", 3, isCorrect = true),
                QuizAnswer("4", "Такого не было", 0),
            )
        ), selectedId = "3", onClickAnswer = {})
    }
}