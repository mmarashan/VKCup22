package io.volgadev.sampleapp.feature.prices_list.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.ktor.feature.prices_list.presentation.AssetsListScreen
import io.volgadev.core.feature_api.FeatureApi

interface AssetsListApi : FeatureApi {
    val assetsListRoute: String
}

internal class AssetsListImpl : AssetsListApi {

    override val assetsListRoute: String = "prices"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder, navController: NavHostController, modifier: Modifier
    ) {
        navGraphBuilder.composable(assetsListRoute) {
            AssetsListScreen(navController = navController, modifier = modifier)
        }
    }
}