package io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.volgadev.core.uikit.composable.chips.TextChip
import io.volgadev.core.uikit.composable.grid.HorizontalMultilineGrid
import io.volgadev.core.uikit.theme.AppColors
import io.volgadev.core.uikit.theme.AppTheme
import io.volgadev.sampleapp.feature.dzentopicspicker.R
import io.volgadev.sampleapp.feature.dzentopicspicker.domain.model.Topic

@Composable
internal fun DzenTopicsPickerScreenContent(
    modifier: Modifier = Modifier, items: Map<Topic, Boolean>, onClickItem: (Topic) -> Unit
) {
    Column(modifier) {
        PriceListHeader()
        Spacer(modifier = Modifier.height(24.dp))
        HorizontalMultilineGrid(
            spacing = 8.dp
        ) {
            items.forEach { item ->
                TextChip(
                    modifier = Modifier
                        .widthIn(80.dp)
                        .clickable(
                            enabled = true,
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { onClickItem.invoke(item.key) }
                        ),
                    text = item.key.name,
                    backgroundColor = if (item.value) {
                        AppColors.primaryOrange
                    } else {
                        AppColors.grayBackground
                    },
                    verticalTextPadding = 10.dp,
                    horizontalTextPadding = 12.dp,
                    cornerRadiusDp = 6.dp,
                    fontSize = 16.sp
                )
            }
        }
    }
}


@Composable
private fun PriceListHeader(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        fontSize = 16.sp,
        text = stringResource(id = R.string.dzen_topics_description)
    )
}


@Preview
@Composable
internal fun PricesListUiPreview() {
    AppTheme {
        DzenTopicsPickerScreenContent(
            modifier = Modifier.padding(16.dp),
            items = mapOf(
                Topic(id = "", name = "Путушествия") to true,
                Topic(id = "", name = "Бизнес") to false
            ), onClickItem = {})
    }
}
