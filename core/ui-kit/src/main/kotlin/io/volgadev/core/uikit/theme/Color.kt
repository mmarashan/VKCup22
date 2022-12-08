package io.volgadev.core.uikit.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


object AppColors {

    val darkBackground = Color(0xFF0C1216)
    val grayBackground = Color(0xFFAFC1DA)
    val lightBackground = Color(0xFFFFFFFF)

    val whiteText = Color(0xFFFFFFFF)
    val blackText = Color(0xFF000000)

    val primaryOrange = Color(0xFFFF7800)

    val shimmerStart = Color(0xFFE8E8F2)
    val shimmerMiddle = Color(0x66E8E8F2)
}

@Composable
@Preview(showBackground = true)
fun AppColorsPreview() {
    Column {
        ColorItem(text = "darkBackground", color = AppColors.darkBackground)
        ColorItem(text = "lightBackground", color = AppColors.lightBackground)
        ColorItem(text = "grayBackground", color = AppColors.grayBackground)
        ColorItem(text = "whiteText", color = AppColors.whiteText)
        ColorItem(text = "blackText", color = AppColors.blackText)
        ColorItem(text = "primaryOrange", color = AppColors.primaryOrange)
    }
}

@Composable
private fun ColorItem(text: String, color: Color) {
    Row {
        Box(
            modifier = Modifier
                .size(20.dp)
                .padding(4.dp)
                .background(color = color)
                .border(width = 0.5.dp, color = Color.Black)
        ) {}
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}
