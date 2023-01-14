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
    onClickToRating: () -> Unit,
    onClickToGapFilling: () -> Unit,
    onClickToGapDragging: () -> Unit,
    onClickToPairMapping: () -> Unit
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
        MainScreenButton(
            modifier = Modifier
                .padding(16.dp),
            text = "Заполнение пропуска в тексте",
            onClick = onClickToGapFilling
        )

        MainScreenButton(
            modifier = Modifier
                .padding(16.dp),
            text = "Перетаскивание вариантов в пропуски",
            onClick = onClickToGapDragging
        )

        MainScreenButton(
            modifier = Modifier
                .padding(16.dp),
            text = "Сопоставление элементов",
            onClick = onClickToPairMapping
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun QuestionnaireDemoRouteScreenPreview() {
    QuestionnaireDemoRouteScreen(
        modifier = Modifier.fillMaxSize(),
        onClickToQuiz = {},
        onClickToRating = {},
        onClickToGapFilling = {},
        onClickToGapDragging = {},
        onClickToPairMapping = {}
    )
}