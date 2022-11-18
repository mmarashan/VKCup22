package io.volgadev.sampleapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.volgadev.core.feature_api.registerFeature
import io.volgadev.sampleapp.feature.prices_list.navigation.AssetsListApi
import org.koin.androidx.compose.get

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val priceApi = get<AssetsListApi>()

    NavHost(
        navController = navController,
        startDestination = priceApi.assetsListRoute,
        modifier = modifier
    ) {
        registerFeature(
            featureApi = priceApi, navController = navController, modifier = modifier
        )
    }
}