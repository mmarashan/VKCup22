package io.volgadev.sampleapp.feature.questionnaire.presentation.quiz.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.volgadev.core.uikit.theme.AppColors

internal enum class QuizButtonState {
    UNSELECTED, CORRECT, INCORRECT
}

@Composable
internal fun QuizButton(
    modifier: Modifier = Modifier,
    text: String,
    additionalText: String? = null,
    textColor: Color = AppColors.whiteText,
    state: QuizButtonState = QuizButtonState.UNSELECTED,
) {
    val backgroundColor: Color = when (state) {
        QuizButtonState.UNSELECTED -> AppColors.grayBackground
        QuizButtonState.CORRECT -> AppColors.primaryGreen
        QuizButtonState.INCORRECT -> AppColors.primaryRed
    }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .height(54.dp)
            .fillMaxWidth()
            .background(
                color = backgroundColor, shape = RoundedCornerShape(percent = 25)
            )
            .clip(CircleShape)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier,
                color = textColor,
                fontSize = 20.sp,
                text = text,
                maxLines = 1
            )
            Spacer(modifier = Modifier.weight(1f))
            if (additionalText != null) {
                Text(
                    modifier = Modifier,
                    color = textColor,
                    fontSize = 16.sp,
                    text = additionalText,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuizButtonPreview() {
    QuizButton(
        text = "Ответ 1",
        additionalText = "99%"
    )
}

@Preview(showBackground = true)
@Composable
private fun QuizButtonPreview2() {
    QuizButton(
        text = "Ответ 1",
        additionalText = "99%",
        state = QuizButtonState.CORRECT
    )
}

@Preview(showBackground = true)
@Composable
private fun QuizButtonPreview3() {
    QuizButton(
        text = "Ответ 1",
        additionalText = "99%",
        state = QuizButtonState.INCORRECT
    )
}