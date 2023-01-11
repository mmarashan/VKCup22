package io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.volgadev.core.uikit.composable.grid.HorizontalMultilineGrid
import io.volgadev.core.uikit.theme.AppColors
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapDraggingItemState
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapDraggingTextItem
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapsDraggingCheckResult

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun GapDraggingComponent(
    modifier: Modifier, state: GapDraggingItemState, onChangeGapValue: (Int, String) -> Unit
) {
    val gapsCheckResults = (state.gapsCheckResult ?: GapsDraggingCheckResult()).gapsCheckingResults

    Column(modifier = modifier) {
        HorizontalMultilineGrid(
            spacing = 8.dp
        ) {
            state.items.forEachIndexed { i, item ->
                when (item) {
                    is GapDraggingTextItem.Gap -> {
                        val boxColor = when (gapsCheckResults[i]) {
                            true -> AppColors.primaryGreen
                            false -> AppColors.primaryRed
                            null -> AppColors.grayBackground
                        }

                        Box(
                            modifier = Modifier
                                .widthIn(64.dp)
                                .height(28.dp)
                                .background(
                                    color = boxColor, shape = RoundedCornerShape(4.dp)
                                ), contentAlignment = Alignment.Center
                        ) {
                            if (item.value != null) {
                                Text(
                                    modifier = Modifier,
                                    text = item.value,
                                    fontSize = 18.sp,
                                    lineHeight = 20.sp,
                                )
                            }
                        }
                    }
                    is GapDraggingTextItem.Word -> {
                        Text(
                            modifier = Modifier,
                            text = item.value,
                            fontSize = 18.sp,
                            lineHeight = 20.sp,
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        HorizontalMultilineGrid(
            spacing = 8.dp
        ) {
            state.tips.forEach { tip ->
                Box(
                    modifier = Modifier
                        .widthIn(64.dp)
                        .height(28.dp)
                        .background(
                            color = AppColors.primaryOrange,
                            shape = RoundedCornerShape(4.dp)
                        ), contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier,
                        text = tip,
                        fontSize = 18.sp,
                        lineHeight = 20.sp,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun GapDraggingComponentPreview() {
    GapDraggingComponent(modifier = Modifier.padding(16.dp), state = GapDraggingItemState(
        id = "1", questionText = "Заполните пропуски в тексте", items = listOf(
            GapDraggingTextItem.Word("У"),
            GapDraggingTextItem.Word("лукоморья"),
            GapDraggingTextItem.Gap(null),
            GapDraggingTextItem.Word("зеленый."),
            GapDraggingTextItem.Word("Злотая"),
            GapDraggingTextItem.Gap(null),
            GapDraggingTextItem.Word("на"),
            GapDraggingTextItem.Gap(null),
            GapDraggingTextItem.Word("том."),
        ), gapsCheckResult = null,
        tips = listOf(
            "дуб", "цепь", "дубе"
        )
    ), onChangeGapValue = { _, _ -> })
}

@Preview(showBackground = true)
@Composable
internal fun GapDraggingComponentPreview2() {
    GapDraggingComponent(modifier = Modifier.padding(16.dp), state = GapDraggingItemState(
        id = "1", questionText = "Заполните пропуски в тексте", items = listOf(
            GapDraggingTextItem.Word("У"),
            GapDraggingTextItem.Word("лукоморья"),
            GapDraggingTextItem.Gap("дуб"),
            GapDraggingTextItem.Word("зеленый."),
            GapDraggingTextItem.Word("Злотая"),
            GapDraggingTextItem.Gap(null),
            GapDraggingTextItem.Word("на"),
            GapDraggingTextItem.Gap("дубе"),
            GapDraggingTextItem.Word("том."),
        ), gapsCheckResult = GapsDraggingCheckResult(
            gapsCheckingResults = mapOf(2 to true, 7 to true)
        ), tips = listOf(
            "цепь"
        )
    ), onChangeGapValue = { _, _ -> })
}