package io.volgadev.sampleapp.feature.questionnaire.presentation.pairmapping.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import io.volgadev.sampleapp.feature.questionnaire.presentation.pairmapping.model.PairItemAnswer
import io.volgadev.sampleapp.feature.questionnaire.presentation.pairmapping.model.PairMappingItemState

@Composable
internal fun PairMappingComponent(
    modifier: Modifier,
    state: PairMappingItemState,
    onChangePairValue: (String, String?) -> Unit
) {
    LongPressDraggable(modifier = modifier) {
        Column {
            Column(

            ) {
                state.pairs.forEach { (key, item) ->
                    PairItem(
                        modifier = Modifier
                            .fillMaxWidth(),
                        key = key,
                        value = item.value,
                        checkingResult = item.checkResult,
                        onDragAnswer = { answer ->
                            onChangePairValue(key, answer)
                        }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            HorizontalMultilineGrid(
                spacing = 8.dp
            ) {
                state.freeItems.forEach { tip ->
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
private fun PairItem(
    modifier: Modifier,
    key: String,
    value: String?,
    checkingResult: Boolean?,
    onDragAnswer: (String?) -> Unit
) {
    DropTarget<String>(
        modifier = Modifier
    ) { answer ->
        answer?.let {
            onDragAnswer(answer)
        }

        val backgroundColor = when (checkingResult) {
            true -> AppColors.primaryGreen
            false -> AppColors.primaryRed
            null -> AppColors.grayBackground
        }

        Row(
            modifier = modifier.clickable {
                if (value?.isNotEmpty() == true) onDragAnswer(null)
            },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 4.dp),
                text = key,
                fontSize = 18.sp,
                lineHeight = 20.sp,
            )

            if (value != null) {
                Text(
                    modifier = Modifier
                        .background(
                            color = backgroundColor,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 4.dp),
                    text = value,
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
internal fun PairMappingComponentPreview() {
    PairMappingComponent(
        modifier = Modifier.padding(16.dp),
        state = PairMappingItemState(
            id = "1",
            questionText = "Найдите соответствие",
            pairs = mapOf(
                "Аргентина" to PairItemAnswer(value = "Буэнос-Айрос"),
                "Португалия" to PairItemAnswer(),
                "Бразилия" to PairItemAnswer(),
                "Россия " to PairItemAnswer()
            ),
            freeItems = listOf("Лиссабон", "Рио-Де-Жанейро", "Москва"),
        ),
        onChangePairValue = { _, _ -> }
    )
}