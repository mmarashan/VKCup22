package io.volgadev.core.uikit.composable.ratingbar

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.volgadev.core.uikit.theme.AppColors


class RatingBarConfig {

    /**
     * size for each star.
     */
    var size: Dp = 26.dp
        private set

    /**
     * padding between each star.
     */
    var padding: Dp = 2.dp
        private set

    /**
     * numStars Sets the number of stars to show.
     */
    var numStars: Int = 5
        private set

    /**
     * isIndicator Whether this rating bar is only an indicator.
     */
    var isIndicator: Boolean = false
        private set

    /**
     * A [Color] representing an active star (or part of it).
     */
    var activeColor: Color = AppColors.primaryOrange
        private set

    /**
     * A [Color] representing a inactive star (or part of it).
     */
    var inactiveColor: Color = AppColors.grayBackground
        private set

    /**
     * Sets the size of the star.
     * @param value the value in Dp
     */
    fun size(value: Dp): RatingBarConfig = apply {
        size = value
    }

    /**
     * Sets the padding between star.
     * @param value the value in Dp.
     */
    fun padding(value: Dp): RatingBarConfig = apply {
        padding = value
    }

    /**
     * Sets whether this rating bar is only an indicator.
     * @param value the value in Boolean.
     */
    fun isIndicator(value: Boolean): RatingBarConfig = apply {
        isIndicator = value
    }

    /**
     * Sets the number of stars to show.
     * @param value the value in Int.
     */
    fun numStars(value: Int): RatingBarConfig = apply {
        numStars = value
    }

    /**
     * Sets the [Color] representing an active star (or part of it).
     * @param value the value in [Color]
     */
    fun activeColor(value: Color): RatingBarConfig = apply {
        activeColor = value
    }

    /**
     * Sets the [Color] representing a inactive star (or part of it).
     * @param value the value in [Color]
     */
    fun inactiveColor(value: Color): RatingBarConfig = apply {
        inactiveColor = value
    }
}