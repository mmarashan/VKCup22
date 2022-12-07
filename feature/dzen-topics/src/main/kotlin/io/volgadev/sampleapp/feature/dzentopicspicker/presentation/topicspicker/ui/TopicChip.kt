package io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.volgadev.core.uikit.theme.AppColors
import io.volgadev.sampleapp.feature.dzentopicspicker.R

/**
 * Длительность анимации в милисекундах
 */
private const val TAB_ANIMATION_DURATION = 150
private const val TAB_HEIGHT_DP = 40
private const val CORNER_SHAPE_PERCENT = 25

/**
 * Чипс темы
 * @param selected выбран ли таб
 * @param onClick обработчик клика на табе
 */
@Composable
internal fun TopicChip(
    modifier: Modifier, text: String, selected: Boolean
) {
    Box(modifier) {
        Box(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = TAB_ANIMATION_DURATION,
                        easing = FastOutSlowInEasing
                    )
                ),
        ) {
            if (selected) {
                SelectedTab(
                    text = text
                )
            } else {
                UnselectedTab(
                    text = text
                )
            }
        }
    }
}

@Composable
private fun SelectedTab(
    text: String,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(TAB_HEIGHT_DP.dp)
            .background(
                color = AppColors.primaryOrange, shape = RoundedCornerShape(CORNER_SHAPE_PERCENT)
            )
            .clip(CircleShape)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 14.dp)
        ) {
            Text(
                modifier = Modifier,
                color = AppColors.whiteText,
                fontSize = 16.sp,
                text = text,
                maxLines = 1
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_ok),
                contentDescription = text,
                tint = AppColors.whiteText
            )
        }
    }
}

@Composable
private fun UnselectedTab(
    text: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(TAB_HEIGHT_DP.dp)
            .background(
                color = AppColors.grayBackground, shape = RoundedCornerShape(CORNER_SHAPE_PERCENT)
            )
            .clip(CircleShape)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 14.dp)
        ) {
            Text(
                color = AppColors.whiteText, text = text, fontSize = 16.sp, maxLines = 1
            )
        }
    }
}

@Preview
@Composable
private fun SelectedTabPreview() {
    TopicChip(
        modifier = Modifier, text = "Тест", selected = true
    )
}

@Preview
@Composable
private fun UnselectedTabPreview() {
    TopicChip(
        modifier = Modifier, text = "Тест", selected = false
    )
}
