package io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.volgadev.core.uikit.composable.grid.HorizontalMultilineGrid
import io.volgadev.core.uikit.composable.input.TextField
import io.volgadev.core.uikit.theme.AppColors
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapDraggingItemState
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapDraggingTextItem
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapsCheckResult

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun GapDraggingComponent(
    modifier: Modifier,
    state: GapDraggingItemState,
    onChangeGapValue: (Int, String) -> Unit
) {
    val gapsCheckResults = (state.gapsCheckResult ?: GapsCheckResult()).gapsCheckingResults

    Box(modifier = modifier) {
        HorizontalMultilineGrid(
            spacing = 8.dp
        ) {
            state.items.forEachIndexed { i, item ->
                when (item) {
                    is GapDraggingTextItem.Gap -> {
                        val textColor = when (gapsCheckResults[i]) {
                            true -> AppColors.primaryGreen
                            false -> AppColors.primaryRed
                            null -> AppColors.blackText
                        }

                        TextField(
                            modifier = Modifier,
                            value = item.value,
                            onValueChange = {
                                onChangeGapValue(i, it)
                            },
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 18.sp, lineHeight = 20.sp
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                textColor = textColor,
                                errorIndicatorColor = AppColors.primaryRed,
                                errorCursorColor = AppColors.primaryRed
                            )
                        )
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
    }
}

@Preview(showBackground = true)
@Composable
internal fun GapDraggingComponentPreview() {
    GapDraggingComponent(
        modifier = Modifier.padding(16.dp), state = GapDraggingItemState(
            id = "1",
            questionText = "Заполните пропуски в тексте",
            items = listOf(
                GapDraggingTextItem.Word("У"),
                GapDraggingTextItem.Word("лукоморья"),
                GapDraggingTextItem.Gap(""),
                GapDraggingTextItem.Word("зеленый."),
                GapDraggingTextItem.Word("Злотая"),
                GapDraggingTextItem.Gap(""),
                GapDraggingTextItem.Word("на"),
                GapDraggingTextItem.Gap(""),
                GapDraggingTextItem.Word("том."),
            ),
        ),
        onChangeGapValue = { _, _ -> }
    )
}

@Preview(showBackground = true)
@Composable
internal fun GapDraggingComponentPreview2() {
    GapDraggingComponent(
        modifier = Modifier.padding(16.dp), state = GapDraggingItemState(
            id = "", questionText = "", items = listOf(
                GapDraggingTextItem.Word("У"),
                GapDraggingTextItem.Word("лукоморья"),
                GapDraggingTextItem.Gap("дуб"),
                GapDraggingTextItem.Word("зеленый."),
                GapDraggingTextItem.Word("Злотая"),
                GapDraggingTextItem.Gap("язь"),
                GapDraggingTextItem.Word("на"),
                GapDraggingTextItem.Gap("дубе"),
                GapDraggingTextItem.Word("том."),
            ), gapsCheckResult = GapsCheckResult(
                gapsCheckingResults = mapOf(2 to true, 5 to false, 7 to true)
            )
        ),
        onChangeGapValue = { _, _ -> }
    )
}