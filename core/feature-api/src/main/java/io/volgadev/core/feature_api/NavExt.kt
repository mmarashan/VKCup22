package io.volgadev.core.feature_api

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun NavGraphBuilder.registerFeature(
    featureApi: FeatureApi,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    featureApi.registerInGraph(
        navGraphBuilder = this,
        navController = navController,
        modifier = modifier
    )
}