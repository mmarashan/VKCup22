package io.volgadev.core.uikit.composable.grid

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

/**
 * Vertically located damage group of elements with
 * transfer to the next line
 */
@Composable
fun HorizontalMultilineGrid(
    modifier: Modifier = Modifier,
    spacing: Dp,
    inverted: Boolean = false,
    content: @Composable () -> Unit
) {
    Layout(
        content = content, modifier = modifier
    ) { measurables, constraints ->
        var currentRow = 0
        var currentOrigin = IntOffset.Zero
        val spacingValue = spacing.toPx().toInt()
        val placeables = measurables.map { measurable ->
            val placeable = measurable.measure(constraints)

            if (currentOrigin.x > 0f && currentOrigin.x + placeable.width > constraints.maxWidth) {
                currentRow += 1
                currentOrigin =
                    currentOrigin.copy(x = 0, y = currentOrigin.y + placeable.height + spacingValue)
            }

            placeable to currentOrigin.also {
                currentOrigin = it.copy(x = it.x + placeable.width + spacingValue)
            }
        }

        layout(width = constraints.maxWidth,
            height = placeables.lastOrNull()?.run { first.height + second.y } ?: 0) {
            placeables.forEach {
                val (placeable, origin) = it

                val originX = if (inverted) {
                    constraints.maxWidth - origin.x - placeable.width
                } else {
                    origin.x
                }

                placeable.place(originX, origin.y)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChipGroupPreview() {
    val items = listOf("Test", "Test-Test", "Test-Test-Test", "Test-Test", "Test", "Test-Test-Test")

    HorizontalMultilineGrid(
        modifier = Modifier.padding(8.dp), spacing = 8.dp
    ) {
        items.forEach { item ->
            Text(
                modifier = Modifier.widthIn(80.dp),
                text = item
            )
        }
    }
}

@Preview(showBackground = true, fontScale = 2f)
@Composable
private fun ChipGroupPreviewScalableText() {
    ChipGroupPreview()
}
