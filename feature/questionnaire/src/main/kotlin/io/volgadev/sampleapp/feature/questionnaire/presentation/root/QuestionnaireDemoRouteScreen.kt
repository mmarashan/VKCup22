package io.volgadev.sampleapp.feature.questionnaire.presentation.root

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.volgadev.core.uikit.composable.button.MainScreenButton

@Composable
internal fun QuestionnaireDemoRouteScreen(
    modifier: Modifier,
    onClickToQuiz: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        MainScreenButton(
            modifier = Modifier.clickable {
                onClickToQuiz.invoke()
            },
            text = "Выбрать ответ"
        )
    }
}