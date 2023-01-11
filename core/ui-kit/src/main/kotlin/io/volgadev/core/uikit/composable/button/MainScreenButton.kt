package io.volgadev.core.uikit.composable.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.volgadev.core.uikit.theme.AppColors


@Composable
fun MainScreenButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = AppColors.whiteText,
    backgroundColor: Color = AppColors.primaryOrange,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
) {
    Button(
        modifier = modifier
            .height(54.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        shape = RoundedCornerShape(20),
        onClick = onClick,
        enabled = enabled
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(
                modifier = Modifier,
                color = textColor,
                fontSize = 16.sp,
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenButtonPreview() {
    MainScreenButton(
        text = "Продолжить"
    )
}


@Preview(showBackground = true)
@Composable
private fun MainScreenButtonDisabledPreview() {
    MainScreenButton(
        text = "Продолжить",
        enabled = false
    )
}