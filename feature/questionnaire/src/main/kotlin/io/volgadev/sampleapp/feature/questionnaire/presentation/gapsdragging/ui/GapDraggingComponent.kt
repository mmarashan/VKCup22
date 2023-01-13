package io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.volgadev.core.uikit.composable.grid.HorizontalMultilineGrid
import io.volgadev.core.uikit.dragdrop.DragTarget
import io.volgadev.core.uikit.dragdrop.DropTarget
import io.volgadev.core.uikit.dragdrop.LongPressDraggable
import io.volgadev.core.uikit.theme.AppColors
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapDraggingItemState
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapDraggingTextItem
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.model.GapsDraggingCheckResult

@Composable
internal fun GapDraggingComponent(
    modifier: Modifier,
    state: GapDraggingItemState,
    onChangeGapValue: (Int, String?) -> Unit
) {
    val checkResults = (state.gapsCheckResult ?: GapsDraggingCheckResult()).gapsCheckingResults

    LongPressDraggable(modifier = modifier) {
        Column {
            HorizontalMultilineGrid(
                spacing = 8.dp
            ) {
                state.items.forEachIndexed { i, item ->
                    when (item) {
                        is GapDraggingTextItem.Gap -> {
                            GapItem(
                                modifier = Modifier
                                    .widthIn(64.dp)
                                    .height(28.dp),
                                item = item,
                                checkingResult = checkResults[i],
                                onDragAnswer = { answer ->
                                    onChangeGapValue(i, answer)
                                }
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

            Spacer(modifier = Modifier.height(32.dp))

            HorizontalMultilineGrid(
                spacing = 8.dp
            ) {
                state.tips.forEach { tip ->
                    TipItem(
                        Modifier
                            .widthIn(64.dp)
                            .height(28.dp),
                        text = tip
                    )
                }
            }
        }
    }
}

@Composable
private fun GapItem(
    modifier: Modifier,
    item: GapDraggingTextItem.Gap,
    checkingResult: Boolean?,
    onDragAnswer: (String?) -> Unit
) {
    DropTarget<String>(
        modifier = Modifier
    ) { answer ->
        answer?.let {
            onDragAnswer(answer)
        }

        val boxColor = when (checkingResult) {
            true -> AppColors.primaryGreen
            false -> AppColors.primaryRed
            null -> AppColors.grayBackground
        }

        val gapText = item.value
        Box(
            modifier = modifier
                .background(
                    color = boxColor,
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable {
                    if (gapText?.isNotEmpty() == true) onDragAnswer(null)
                },
            contentAlignment = Alignment.Center
        ) {
            if (gapText != null) {
                Text(
                    modifier = Modifier,
                    text = gapText,
                    fontSize = 18.sp,
                    lineHeight = 20.sp,
                )
            }
        }
    }
}

@Composable
private fun TipItem(
    modifier: Modifier,
    text: String
) {
    DragTarget(
        modifier = Modifier,
        dataToDrop = text
    ) {
        Box(
            modifier = modifier.background(
                color = AppColors.primaryOrange,
                shape = RoundedCornerShape(4.dp)
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = text,
                fontSize = 18.sp,
                lineHeight = 20.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun GapDraggingComponentPreview() {
    GapDraggingComponent(
        modifier = Modifier.padding(16.dp),
        state = GapDraggingItemState(
            id = "1",
            questionText = "Заполните пропуски в тексте",
            items = listOf(
                GapDraggingTextItem.Word("У"),
                GapDraggingTextItem.Word("лукоморья"),
                GapDraggingTextItem.Gap(null),
                GapDraggingTextItem.Word("зеленый."),
                GapDraggingTextItem.Word("Злотая"),
                GapDraggingTextItem.Gap(null),
                GapDraggingTextItem.Word("на"),
                GapDraggingTextItem.Gap(null),
                GapDraggingTextItem.Word("том."),
            ),
            gapsCheckResult = null,
            tips = listOf(
                "дуб",
                "цепь",
                "дубе"
            )
        ),
        onChangeGapValue = { _, _ -> }
    )
}

@Preview(showBackground = true)
@Composable
internal fun GapDraggingComponentPreview2() {
    GapDraggingComponent(
        modifier = Modifier.padding(16.dp),
        state = GapDraggingItemState(
            id = "1",
            questionText = "Заполните пропуски в тексте",
            items = listOf(
                GapDraggingTextItem.Word("У"),
                GapDraggingTextItem.Word("лукоморья"),
                GapDraggingTextItem.Gap("дуб"),
                GapDraggingTextItem.Word("зеленый."),
                GapDraggingTextItem.Word("Злотая"),
                GapDraggingTextItem.Gap(null),
                GapDraggingTextItem.Word("на"),
                GapDraggingTextItem.Gap("дубе"),
                GapDraggingTextItem.Word("том."),
            ),
            gapsCheckResult = GapsDraggingCheckResult(
                gapsCheckingResults = mapOf(2 to true, 7 to true)
            ),
            tips = listOf(
                "русалка"
            )
        ),
        onChangeGapValue = { _, _ -> }
    )
}
