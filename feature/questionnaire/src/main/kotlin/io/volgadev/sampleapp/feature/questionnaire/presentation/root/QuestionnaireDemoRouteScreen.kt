package io.volgadev.sampleapp.feature.questionnaire.presentation.root

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.volgadev.core.uikit.composable.button.MainScreenButton

@Composable
internal fun QuestionnaireDemoRouteScreen(
    modifier: Modifier,
    onClickToQuiz: () -> Unit,
    onClickToRating: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        MainScreenButton(
            modifier = Modifier
                .padding(16.dp),
            text = "Выбор ответа 1 из 4",
            onClick = onClickToQuiz
        )
        MainScreenButton(
            modifier = Modifier
                .padding(16.dp),
            text = "Поставить оценку",
            onClick = onClickToRating
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun QuestionnaireDemoRouteScreenPreview() {
    QuestionnaireDemoRouteScreen(
        modifier = Modifier.fillMaxSize(),
        onClickToQuiz = {},
        onClickToRating = {}
    )
}