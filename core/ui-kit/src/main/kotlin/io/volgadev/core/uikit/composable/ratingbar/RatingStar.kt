package io.volgadev.core.uikit.composable.ratingbar

import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.volgadev.core.uikit.theme.AppColors

private const val strokeWidth = 1f

@Composable
internal fun RatingStar(
    @FloatRange(from = 0.0, to = 1.0) fraction: Float,
    config: RatingBarConfig,
    modifier: Modifier = Modifier,
) {
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

    Box(modifier = modifier) {
        FilledStar(fraction, config.activeColor, isRtl)
        EmptyStar(fraction, config, isRtl)
    }
}

@Composable
private fun FilledStar(fraction: Float, activeColor: Color, isRtl: Boolean) = Canvas(
    modifier = Modifier
        .fillMaxSize()
        .clip(
            if (isRtl) {
                rtlFilledStarFractionalShape(fraction = fraction)
            } else {
                FractionalRectangleShape(0f, fraction)
            }
        )
) {
    val path = Path().addStar(size)

    drawPath(path, color = activeColor, style = Fill) // Filled Star
    drawPath(path, color = activeColor, style = Stroke(width = strokeWidth)) // Border
}

@Composable
private fun EmptyStar(
    fraction: Float,
    config: RatingBarConfig,
    isRtl: Boolean
) =
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .clip(
                if (isRtl) {
                    rtlEmptyStarFractionalShape(fraction = fraction)
                } else {
                    FractionalRectangleShape(fraction, 1f)
                }
            )
    ) {
        val path = Path().addStar(size)
        drawPath(path, color = config.inactiveColor, style = Fill)
    }

@Preview(showBackground = true)
@Composable
private fun EmptyRatingStarPreview() {
    RatingStar(
        fraction = 0f,
        config = RatingBarConfig()
            .activeColor(AppColors.primaryRed)
            .inactiveColor(AppColors.grayBackground),
        modifier = Modifier.size(20.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun PartialRatingStarPreview() {
    RatingStar(
        fraction = 0.7f,
        config = RatingBarConfig()
            .activeColor(AppColors.primaryRed)
            .inactiveColor(AppColors.primaryOrange),
        modifier = Modifier.size(20.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun FullRatingStarPreview() {
    RatingStar(
        fraction = 1f,
        config = RatingBarConfig()
            .activeColor(AppColors.primaryOrange)
            .inactiveColor(AppColors.primaryOrange),
        modifier = Modifier.size(20.dp)
    )
}

private fun rtlEmptyStarFractionalShape(fraction: Float): FractionalRectangleShape {
    return if (fraction == 1f || fraction == 0f) {
        FractionalRectangleShape(fraction, 1f)
    } else {
        FractionalRectangleShape(0f, 1f - fraction)
    }
}

private fun rtlFilledStarFractionalShape(fraction: Float): FractionalRectangleShape {
    return if (fraction == 0f || fraction == 1f) {
        FractionalRectangleShape(0f, fraction)
    } else {
        FractionalRectangleShape(1f - fraction, 1f)
    }
}