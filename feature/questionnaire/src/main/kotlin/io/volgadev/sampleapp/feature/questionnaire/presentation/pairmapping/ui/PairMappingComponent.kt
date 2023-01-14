package io.volgadev.sampleapp.feature.questionnaire.presentation.pairmapping.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.volgadev.core.uikit.composable.grid.HorizontalMultilineGrid
import io.volgadev.core.uikit.dragdrop.DragTarget
import io.volgadev.core.uikit.dragdrop.DropTarget
import io.volgadev.core.uikit.dragdrop.LongPressDraggable
import io.volgadev.core.uikit.theme.AppColors
import io.volgadev.sampleapp.feature.questionnaire.presentation.common.ui.TextInBox
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
                        modifier = Modifier.fillMaxWidth(),
                        key = key,
                        value = item.value,
                        checkingResult = item.checkResult,
                        onDragAnswer = { answer ->
                            onChangePairValue(key, answer)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            HorizontalMultilineGrid(
                spacing = 16.dp
            ) {
                state.freeItems.forEach { tip ->
                    TipItem(
                        Modifier,
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
            modifier = modifier
                .clickable {
                    if (value?.isNotEmpty() == true) onDragAnswer(null)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextInBox(
                modifier = Modifier,
                text = key,
                backgroundColor = backgroundColor
            )

            if (value != null) {
                TextInBox(
                    modifier = Modifier,
                    text = value,
                    backgroundColor = backgroundColor
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
        TextInBox(
            modifier = modifier,
            text = text,
            backgroundColor = AppColors.primaryOrange
        )
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