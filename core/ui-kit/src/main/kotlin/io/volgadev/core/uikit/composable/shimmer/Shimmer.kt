package io.volgadev.core.uikit.composable.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import io.volgadev.core.uikit.theme.AppColors

/**
 * Шиммер на основе accompanist
 */
@Composable
fun Shimmer(
    modifier: Modifier,
    cornerRadius: Dp = 8.dp
) {
    Box(
        modifier = modifier.shimmer(
            visible = true,
            cornerRadius = cornerRadius
        )
    )
}


/**
 * Добавление шиммера на любой composable
 */
fun Modifier.shimmer(
    visible: Boolean,
    cornerRadius: Dp
) = this.placeholder(
    visible = visible,
    shape = RoundedCornerShape(cornerRadius),
    highlight = PlaceholderHighlight.shimmer(highlightColor = AppColors.shimmerStart),
    color = AppColors.shimmerMiddle
)
