package io.volgadev.core.uikit.composable.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import io.volgadev.core.uikit.theme.AppColors
import io.volgadev.sampleapp.core.uikit.R
import kotlin.math.roundToInt

/**
 * AppBar c кнопкой "назад" и "закрыть"
 * @param onClickBack callback нажания на назад; если не определен, то иконка не показывается
 * @param onClickClose callback нажания на закрыть; если не определен, то иконка не показывается
 */
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier.fillMaxWidth(),
    onClickBack: (() -> Unit)? = null,
    onClickClose: (() -> Unit)? = null,
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = AppColors.blackText
) {
    TopAppBar(
        modifier = modifier,
        onClickBack = onClickBack,
        onClickClose = onClickClose,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        content = null
    )
}

/**
 * AppBar c заголовком, кнопкой "назад" и "закрыть"
 *
 * @param title текст заголовка
 * @param onClickBack callback нажания на назад; если не определен, то иконка не показывается
 * @param onClickClose callback нажания на закрыть; если не определен, то иконка не показывается
 */
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onClickBack: (() -> Unit)? = null,
    onClickClose: (() -> Unit)? = null,
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = AppColors.blackText,
) {
    TopAppBar(modifier = modifier,
        onClickBack = onClickBack,
        onClickClose = onClickClose,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        content = {
            Text(
                text = title,
                color = contentColor,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        })
}

/**
 * AppBar c контентом, кнопкой "назад" и "закрыть"
 *
 * @param onClickBack callback нажания на назад; если не определен, то иконка не показывается
 * @param onClickClose callback нажания на закрыть; если не определен, то иконка не показывается
 * @param content контент, напр. прогресс бар или текст
 */
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    onClickBack: (() -> Unit)? = null,
    onClickClose: (() -> Unit)? = null,
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = AppColors.blackText,
    content: @Composable (() -> Unit)?,
) {
    TopAppBar(modifier = modifier,
        onClickBack = onClickBack,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        content = content,
        actions = {
            onClickClose?.let {
                IconButton(
                    onClick = it
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = "",
                        tint = contentColor
                    )
                }
            }
        })
}

/**
 * AppBar c контентом, кнопкой "назад" и "закрыть"
 *
 * @param onClickBack callback нажания на назад; если не определен, то иконка не показывается
 * @param content контент, напр. прогресс бар или текст
 * @see [https://androidx.tech/artifacts/compose.material3/material3/1.0.0-alpha15-source/androidx/compose/material3/AppBar.kt.html] -
 * Оригинал взят и адаптирован из Material3: TopAppBarLayout
 */
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    onClickBack: (() -> Unit)? = null,
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = AppColors.blackText,
    content: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null
) {
    /**
     * Высота TopAppBar
     */
    val layoutHeight = LocalDensity.current.run {
        56.dp.toPx().roundToInt()
    }

    Layout(modifier = Modifier
        .background(backgroundColor)
        .statusBarsPadding()
        .padding(horizontal = 4.dp)
        .then(modifier), content = {
        CompositionLocalProvider(

        ) {
            Box(modifier = Modifier.layoutId("navBack")) {
                onClickBack?.let {
                    IconButton(
                        onClick = onClickBack,
                        modifier = Modifier
                            .testTag("BackArrow")
                            .align(Alignment.Center)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back),
                            contentDescription = "",
                            tint = contentColor
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .layoutId("title")
                    .wrapContentSize()
            ) {
                content?.let {
                    content()
                }
            }
            Box(modifier = Modifier.layoutId("actions")) {
                actions?.let {
                    actions()
                }
            }
        }
    }) { measurables, constraints ->
        val navBackPlaceable =
            measurables.first { it.layoutId == "navBack" }.measure(constraints.copy(minWidth = 0))
        val navClosePlaceable =
            measurables.first { it.layoutId == "actions" }.measure(constraints.copy(minWidth = 0))

        val maxTitleWidth = if (constraints.maxWidth == Constraints.Infinity) {
            constraints.maxWidth
        } else {
            (constraints.maxWidth - navBackPlaceable.width - navClosePlaceable.width).coerceAtLeast(
                0
            )
        }
        val titlePlaceable = measurables.first { it.layoutId == "title" }
            .measure(constraints.copy(minWidth = 0, maxWidth = maxTitleWidth))

        layout(constraints.maxWidth, layoutHeight) {
            // Back arrow icon
            navBackPlaceable.placeRelative(
                x = 0, y = (layoutHeight - navBackPlaceable.height) / 2
            )

            // Title
            titlePlaceable.placeRelative(
                x = (constraints.maxWidth - titlePlaceable.width) / 2,
                y = (layoutHeight - titlePlaceable.height) / 2
            )

            // Action icons
            navClosePlaceable.placeRelative(
                x = constraints.maxWidth - navClosePlaceable.width,
                y = (layoutHeight - navClosePlaceable.height) / 2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTopAppBar1() {
    TopAppBar(title = "Заголовок", onClickClose = {}, onClickBack = {})
}

@Preview(showBackground = true)
@Composable
private fun PreviewTopAppBar2() {
    TopAppBar(title = "Очень длинный длинный длинный длинный заголовок",
        onClickClose = {},
        onClickBack = {})
}

@Preview(showBackground = true)
@Composable
private fun PreviewTopAppBar() {
    TopAppBar(title = "Заголовок", onClickBack = {})
}

@Preview(showBackground = true)
@Composable
private fun PreviewTopAppBarContentAndClose() {
    TopAppBar(title = "Заголовок", onClickClose = {})
}

@Preview(showBackground = true)
@Composable
private fun PreviewTopAppBarNoContent() {
    TopAppBar(onClickClose = {}, onClickBack = {})
}

@Preview(showBackground = true)
@Composable
private fun PreviewTopAppBarNoContentAndBackButton() {
    TopAppBar(onClickClose = {})
}

@Preview(showBackground = true)
@Composable
private fun PreviewTopAppBarNoContentAndCloseButton() {
    TopAppBar(onClickBack = {})
}
