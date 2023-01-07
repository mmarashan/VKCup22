package io.volgadev.core.uikit.composable.ratingbar

import android.view.MotionEvent
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RatingBar(
    value: Float,
    modifier: Modifier = Modifier,
    config: RatingBarConfig = RatingBarConfig(),
    onValueChange: (Float) -> Unit,
    onRatingChanged: (Float) -> Unit
) {
    var rowSize by remember { mutableStateOf(Size.Zero) }
    var lastDraggedValue by remember { mutableStateOf(0f) }
    val direction = LocalLayoutDirection.current

    Row(modifier = modifier
        .onSizeChanged { rowSize = it.toSize() }
        .pointerInput(
            Unit
        ) {
            //handling dragging events
            detectHorizontalDragGestures(
                onDragEnd = {
                    if (config.isIndicator) {
                        return@detectHorizontalDragGestures
                    }
                    onRatingChanged(lastDraggedValue)
                },
                onDragCancel = {},
                onDragStart = {},
                onHorizontalDrag = { change, _ ->
                    if (config.isIndicator) {
                        return@detectHorizontalDragGestures
                    }
                    change.consume()
                    val x1 = change.position.x.coerceIn(0f, rowSize.width)
                    val calculatedStars =
                        RatingBarUtils.calculateStars(
                            x1,
                            rowSize.width,
                            config.numStars,
                            config.padding.value.toInt()
                        )
                    var newValue =
                        calculatedStars
                            .coerceIn(0f, config.numStars.toFloat())

                    if (direction == LayoutDirection.Rtl) {
                        newValue = config.numStars - newValue
                    }
                    onValueChange(newValue)
                    lastDraggedValue = newValue
                }
            )
        }
        .pointerInteropFilter {
            if (config.isIndicator) {
                return@pointerInteropFilter false
            }
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    //handling when click events
                    val calculatedStars =
                        RatingBarUtils.calculateStars(
                            it.x,
                            rowSize.width,
                            config.numStars,
                            config.padding.value.toInt()
                        )
                    var newValue =
                        calculatedStars
                            .coerceIn(0f, config.numStars.toFloat())
                    if (direction == LayoutDirection.Rtl) {
                        newValue = config.numStars - newValue
                    }
                    onValueChange(newValue)
                    onRatingChanged(newValue)
                }
            }
            true
        }) {
        ComposeStars(value, config)
    }
}

@Composable
private fun ComposeStars(
    value: Float,
    config: RatingBarConfig
) {

    val ratingPerStar = 1f
    var remainingRating = value

    Row(modifier = Modifier) {
        for (i in 1..config.numStars) {
            val starRating = when {
                remainingRating == 0f -> {
                    0f
                }
                remainingRating >= ratingPerStar -> {
                    remainingRating -= ratingPerStar
                    1f
                }
                else -> {
                    val fraction = remainingRating / ratingPerStar
                    remainingRating = 0f
                    fraction
                }
            }
            RatingStar(
                fraction = starRating,
                config = config,
                modifier = Modifier
                    .padding(
                        start = if (i > 1) config.padding else 0.dp,
                        end = if (i < config.numStars) config.padding else 0.dp
                    )
                    .size(size = config.size)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RatingBarPreview() {
    var rating by remember { mutableStateOf(3.3f) }
    RatingBar(
        value = rating,
        config = RatingBarConfig(),
        onValueChange = {
            rating = it
        }
    ) {}
}