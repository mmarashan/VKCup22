package io.volgadev.sampleapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.insets.ProvideWindowInsets
import io.volgadev.core.uikit.theme.AppTheme
import io.volgadev.sampleapp.navigation.AppNavGraph

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