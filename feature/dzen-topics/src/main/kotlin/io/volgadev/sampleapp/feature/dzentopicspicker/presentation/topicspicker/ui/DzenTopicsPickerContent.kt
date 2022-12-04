package io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.volgadev.core.uikit.composable.chips.TextChip
import io.volgadev.core.uikit.composable.grid.HorizontalMultilineGrid
import io.volgadev.core.uikit.theme.AppColors
import io.volgadev.sampleapp.feature.dzentopicspicker.R
import io.volgadev.sampleapp.feature.dzentopicspicker.domain.model.Topic

@Composable
internal fun DzenTopicsPickerScreenContent(
    modifier: Modifier = Modifier,
    items: List<Topic>,
    onClickItem: (Topic) -> Unit
) {
    Column(modifier) {
        PriceListHeader(
            modifier = Modifier.padding(16.dp)
        )
        HorizontalMultilineGrid(
            modifier = Modifier.padding(16.dp),
            spacing = 8.dp
        ) {
            items.forEach { item ->
                TextChip(
                    modifier = Modifier
                        .widthIn(80.dp)
                        .clickable { onClickItem.invoke(item) },
                    text = item.name,
                    backgroundColor = AppColors.grayBackground,
                    verticalTextPadding = 8.dp,
                    horizontalTextPadding = 20.dp,
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
    Surface {
        DzenTopicsPickerScreenContent(
            items = listOf(
                Topic(id = "", name = "Путушествия"),
                Topic(id = "", name = "Бизнес")
            ),
            onClickItem = {}
        )
    }
}
