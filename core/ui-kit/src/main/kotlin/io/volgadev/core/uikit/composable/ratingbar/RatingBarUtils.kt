package io.volgadev.core.uikit.composable.ratingbar

internal object RatingBarUtils {

    /**
     *  @return selected or dragged stars
     *  float value that should be selected
     * */
    fun calculateStars(
        draggedWidth: Float,
        width: Float,
        numStars: Int,
        padding: Int
    ): Float {
        var overAllComposeWidth = width
        val spacerWidth = numStars * (2 * padding)

        //removing padding's width
        overAllComposeWidth -= spacerWidth
        return if (draggedWidth != 0f) {
            ((draggedWidth / overAllComposeWidth) * numStars)
        } else 0f
    }
}