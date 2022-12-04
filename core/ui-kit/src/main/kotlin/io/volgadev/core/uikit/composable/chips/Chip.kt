package io.volgadev.core.uikit.composable.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.volgadev.core.uikit.theme.AppColors

/**
 * Chip
 * @param modifier
 * @param backgroundColor
 * @param cornerRadiusDp
 * @param content
 */
@Composable
fun Chip(
    modifier: Modifier = Modifier,
    backgroundColor: Color = AppColors.grayBackground,
    cornerRadiusDp: Dp = 20.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(cornerRadiusDp)
            ),
        contentAlignment = Alignment.Center,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun ChipPreview() {
    Column(
        modifier = Modifier
            .padding(all = 8.dp)
            .width(320.dp)
    ) {
        Chip {
            Text(
                text = "Test",
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Chip(
            backgroundColor = AppColors.grayBackground
        ) {
            Text(
                text = "Test test",
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Chip(
            modifier = Modifier.widthIn(120.dp),
            backgroundColor = AppColors.grayBackground
        ) {
            Text(
                text = "Test test tests",
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            )
        }
    }
}