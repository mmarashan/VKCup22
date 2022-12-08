package io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.volgadev.core.uikit.composable.button.MainScreenButton
import io.volgadev.core.uikit.composable.grid.HorizontalMultilineGrid
import io.volgadev.core.uikit.theme.AppColors
import io.volgadev.core.uikit.theme.AppTheme
import io.volgadev.sampleapp.feature.dzentopicspicker.R
import io.volgadev.sampleapp.feature.dzentopicspicker.domain.model.Topic

@Composable
internal fun DzenTopicsPickerScreenContent(
    modifier: Modifier = Modifier,
    items: Map<Topic, Boolean>,
    isNextButtonVisible: Boolean,
    isSkipButtonVisible: Boolean,
    onClickItem: (Topic) -> Unit,
    onClickNext: () -> Unit,
    onClickSkip: () -> Unit
) {
    Scaffold(
        modifier, floatingActionButton = {
            Column {
                if (isNextButtonVisible) {
                    MainScreenButton(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clickable { onClickNext.invoke() },
                        text = stringResource(R.string.next_button_text),
                        backgroundColor = AppColors.primaryOrange
                    )
                }
                if (isSkipButtonVisible) {
                    MainScreenButton(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clickable { onClickSkip.invoke() },
                        text = stringResource(R.string.skip_button_text),
                        backgroundColor = AppColors.grayBackground
                    )
                }
            }
        }, floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            DzenTopicsHeader()
            Spacer(modifier = Modifier.height(24.dp))
            HorizontalMultilineGrid(spacing = 8.dp) {
                items.forEach { item ->
                    TopicChip(
                        modifier = Modifier.clickable(enabled = true,
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { onClickItem.invoke(item.key) }),
                        text = item.key.name,
                        selected = item.value
                    )
                }
            }
        }
    }
}


@Preview
@Composable
internal fun PricesListUiPreview() {
    AppTheme {
        Box(
            modifier = Modifier.background(
                color = AppColors.whiteText
            )
        ) {
            DzenTopicsPickerScreenContent(modifier = Modifier.padding(16.dp),
                items = mapOf(
                    Topic(id = "", name = "Путушествия") to true,
                    Topic(id = "", name = "Бизнес") to false
                ),
                isNextButtonVisible = true,
                isSkipButtonVisible = false,
                onClickItem = {},
                onClickNext = {},
                onClickSkip = {}
            )
        }
    }
}
