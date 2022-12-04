package io.volgadev.core.uikit.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    h1 = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 34.sp,
        lineHeight = 41.sp,
        letterSpacing = 0.01.sp
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = (-0.01).sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 21.sp,
        lineHeight = 25.sp,
        letterSpacing = 0.sp
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.01.sp
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.01.sp
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 14.sp,
        lineHeight = 18.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 13.sp,
        lineHeight = 18.sp
    ),
    caption = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 11.sp,
        lineHeight = 13.sp,
        letterSpacing = 0.01.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp
    )
)