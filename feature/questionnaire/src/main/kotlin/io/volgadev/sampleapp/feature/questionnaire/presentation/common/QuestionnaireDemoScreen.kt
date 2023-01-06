package io.volgadev.sampleapp.feature.questionnaire.presentation.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.volgadev.core.uikit.composable.appbar.TopAppBar
import io.volgadev.core.uikit.composable.button.MainScreenButton
import io.volgadev.core.uikit.theme.AppColors
import io.volgadev.sampleapp.feature.questionnaire.R

/**
 * Шаблон экрана демонстрации вида сбора обратной связи
 */
@Composable
internal fun QuestionnaireDemoScreen(
    modifier: Modifier,
    onClickBack: () -> Unit,
    onClickNext: () -> Unit,
    isNextEnabled: Boolean = false,
    content: @Composable () -> Unit
) {

    Scaffold(modifier = modifier, topBar = {
        TopAppBar(
            onClickBack = onClickBack
        )
    }, floatingActionButton = {
        MainScreenButton(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(R.string.next_quiz),
            backgroundColor = AppColors.darkBackground,
            enabled = isNextEnabled,
            onClick = onClickNext
        )
    }, floatingActionButtonPosition = FabPosition.Center
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
internal fun QuestionnaireDemoScreenPreview() {
    QuestionnaireDemoScreen(
        modifier = Modifier.fillMaxSize(),
        onClickBack = {},
        onClickNext = {},
        isNextEnabled = true,
        content = {})
}