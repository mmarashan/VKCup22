package io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.ui

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
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.model.GapFillingItemState
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.model.GapFillingTextItem
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.model.GapsCheckResult

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun GapFillingComponent(
    modifier: Modifier,
    state: GapFillingItemState,
    onChangeGapValue: (Int, String) -> Unit
) {
    val gapsCheckResults = (state.gapsCheckResult ?: GapsCheckResult()).gapsCheckingResults

    Box(modifier = modifier) {
        HorizontalMultilineGrid(
            spacing = 8.dp
        ) {
            state.items.forEachIndexed { i, item ->
                when (item) {
                    is GapFillingTextItem.Gap -> {
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
                    is GapFillingTextItem.Word -> {
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
internal fun GapFillingComponentPreview() {
    GapFillingComponent(
        modifier = Modifier.padding(16.dp), state = GapFillingItemState(
            id = "1",
            questionText = "Заполните пропуски в тексте",
            items = listOf(
                GapFillingTextItem.Word("У"),
                GapFillingTextItem.Word("лукоморья"),
                GapFillingTextItem.Gap(""),
                GapFillingTextItem.Word("зеленый."),
                GapFillingTextItem.Word("Злотая"),
                GapFillingTextItem.Gap(""),
                GapFillingTextItem.Word("на"),
                GapFillingTextItem.Gap(""),
                GapFillingTextItem.Word("том."),
            ),
        ),
        onChangeGapValue = { _, _ -> }
    )
}

@Preview(showBackground = true)
@Composable
internal fun GapFillingComponentPreview2() {
    GapFillingComponent(
        modifier = Modifier.padding(16.dp), state = GapFillingItemState(
            id = "", questionText = "", items = listOf(
                GapFillingTextItem.Word("У"),
                GapFillingTextItem.Word("лукоморья"),
                GapFillingTextItem.Gap("дуб"),
                GapFillingTextItem.Word("зеленый."),
                GapFillingTextItem.Word("Злотая"),
                GapFillingTextItem.Gap("язь"),
                GapFillingTextItem.Word("на"),
                GapFillingTextItem.Gap("дубе"),
                GapFillingTextItem.Word("том."),
            ), gapsCheckResult = GapsCheckResult(
                gapsCheckingResults = mapOf(2 to true, 5 to false, 7 to true)
            )
        ),
        onChangeGapValue = { _, _ -> }
    )
}