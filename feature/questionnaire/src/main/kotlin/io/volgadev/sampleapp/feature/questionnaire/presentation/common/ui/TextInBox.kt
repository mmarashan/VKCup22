package io.volgadev.sampleapp.feature.questionnaire.presentation.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.volgadev.core.uikit.theme.AppColors

@Composable
fun TextInBox(
    modifier: Modifier,
    text: String,
    backgroundColor: Color = AppColors.primaryOrange
) {
    Box(
        modifier = modifier.background(
            color = backgroundColor,
            shape = RoundedCornerShape(4.dp)
        ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            text = text,
            fontSize = 18.sp,
            lineHeight = 20.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun TextInBoxPreview() {
    TextInBox(modifier = Modifier.padding(16.dp), text = "Текст")
}