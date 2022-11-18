package io.volgadev.sampleapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.insets.ProvideWindowInsets
import io.volgadev.sampleapp.navigation.AppNavGraph
import io.volgadev.sampleapp.ui.theme.AppTheme

@Composable
fun AppContent(navController: NavHostController) {
    ProvideWindowInsets {
        AppTheme {
            AppNavGraph(
                navController = navController, modifier = Modifier
            )
        }
    }
}