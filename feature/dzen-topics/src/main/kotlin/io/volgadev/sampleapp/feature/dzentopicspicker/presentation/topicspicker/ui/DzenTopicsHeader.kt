package io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.volgadev.sampleapp.feature.dzentopicspicker.R

@Composable
internal fun DzenTopicsHeader(
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.weight(10f),
            fontSize = 18.sp,
            text = stringResource(id = R.string.dzen_topics_description)
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .size(36.dp)
                .weight(1f),
            painter = painterResource(R.drawable.dzen_logo),
            contentDescription = ""
        )
    }
}
