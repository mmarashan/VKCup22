package io.volgadev.sampleapp.feature.prices_list.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import io.volgadev.core.feature_api.FeatureApi
import io.volgadev.sampleapp.feature.prices_list.presentation.AssetsListScreen

interface AssetsListApi : FeatureApi {
    val assetsListRoute: String
}

internal class AssetsListImpl : AssetsListApi {

    override val assetsListRoute: String = "prices"

    override fun registerInGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(assetsListRoute) {
            AssetsListScreen(navController = navController, modifier = modifier)
        }
    }
}