package io.volgadev.core.uikit.composable.chips

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import io.volgadev.core.uikit.theme.AppColors

@Composable
fun TextChip(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    cornerRadiusDp: Dp = 20.dp,
    backgroundColor: Color = AppColors.grayBackground,
    fontSize: TextUnit = TextUnit.Unspecified,
    textColor: Color = AppColors.whiteText,
    textStyle: TextStyle = TextStyle(),
    verticalTextPadding: Dp = 6.dp,
    horizontalTextPadding: Dp = 12.dp,
    onClick: () -> Unit = { }
) {
    Chip(
        modifier = modifier,
        cornerRadiusDp = cornerRadiusDp,
        enabled = enabled,
        backgroundColor = backgroundColor,
        onClick = onClick
    ) {
        Text(
            text = text,
            maxLines = 1,
            style = textStyle,
            color = textColor,
            fontSize = fontSize,
            modifier = Modifier.padding(
                horizontal = horizontalTextPadding,
                vertical = verticalTextPadding
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextTagPreview() {
    Column(
        modifier = Modifier.padding(all = 8.dp).width(300.dp)
    ) {
        TextChip(
            text = "Test"
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextChip(
            text = "Test Test",
            backgroundColor = AppColors.primaryOrange
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextChip(
            modifier = Modifier.widthIn(140.dp),
            text = "Test Test Test",
            backgroundColor = AppColors.grayBackground
        )
    }
}
