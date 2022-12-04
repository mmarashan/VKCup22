package io.volgadev.sampleapp.feature.dzentopicspicker.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import io.volgadev.core.feature_api.FeatureApi
import io.volgadev.sampleapp.feature.dzentopicspicker.presentation.topicspicker.DzenTopicsPickerScreen

interface DzenTopicsPickerApi : FeatureApi {
    val dzenTopicsPickerRoute: String
}

internal class DzenTopicsPickerImpl : DzenTopicsPickerApi {

    override val dzenTopicsPickerRoute: String = "dzenTopics"

    override fun registerInGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(dzenTopicsPickerRoute) {
            DzenTopicsPickerScreen(navController = navController, modifier = modifier)
        }
    }
}