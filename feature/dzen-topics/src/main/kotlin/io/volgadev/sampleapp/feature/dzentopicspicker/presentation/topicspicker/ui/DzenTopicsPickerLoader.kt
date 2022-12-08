package io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.volgadev.core.uikit.composable.grid.HorizontalMultilineGrid
import io.volgadev.core.uikit.composable.shimmer.Shimmer
import io.volgadev.core.uikit.theme.AppColors
import io.volgadev.core.uikit.theme.AppTheme
import kotlin.random.Random

@Composable
internal fun DzenTopicsPickerScreenLoader(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            DzenTopicsHeader()
            Spacer(modifier = Modifier.height(24.dp))
            HorizontalMultilineGrid(spacing = 8.dp) {
                (1..20).forEach {
                    Shimmer(
                        modifier = Modifier
                            .height(TAB_HEIGHT_DP.dp)
                            .width(Random.nextInt(100, 164).dp)
                            .padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
internal fun DzenTopicsPickerScreenLoaderPreview() {
    AppTheme {
        Box(
            modifier = Modifier.background(
                color = AppColors.whiteText
            )
        ) {
            DzenTopicsPickerScreenLoader(
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
