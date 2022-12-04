package io.volgadev.core.uikit.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = AppColors.primaryOrange,
    primaryVariant = AppColors.grayBackground,
    secondary = AppColors.darkBackground,
    background = AppColors.darkBackground
)

private val LightColorPalette = lightColors(
    primary = AppColors.primaryOrange,
    primaryVariant = AppColors.grayBackground,
    secondary = AppColors.darkBackground,
    background = AppColors.lightBackground
)

@Composable
fun AppTheme(
    darkTheme: Boolean = false/*isSystemInDarkTheme()*/,
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = AppShapes,
        content = content
    )
}