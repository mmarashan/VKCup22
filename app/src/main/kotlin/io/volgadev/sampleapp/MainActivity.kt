package io.volgadev.sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.volgadev.sampleapp.feature.prices_list.navigation.AssetsListApi
import io.volgadev.sampleapp.ui.theme.KtorExampleTheme
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorExampleTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    val priceApi = get<AssetsListApi>()

                    NavHost(
                        navController = navController, startDestination = priceApi.assetsListRoute
                    ) {
                        priceApi.registerGraph(
                            navGraphBuilder = this, navController = navController
                        )
                    }
                }
            }
        }
    }
}
