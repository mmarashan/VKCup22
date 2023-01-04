package io.volgadev.sampleapp.feature.questionnaire.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import io.volgadev.core.feature_api.FeatureApi
import io.volgadev.sampleapp.feature.questionnaire.presentation.topicspicker.QuestionnaireDemoScreen

interface QuestionnaireApi : FeatureApi {
    val dzenTopicsPickerRoute: String
}

internal class QuestionnaireImpl : QuestionnaireApi {

    override val dzenTopicsPickerRoute: String = "dzenTopics"

    override fun registerInGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(dzenTopicsPickerRoute) {
            QuestionnaireDemoScreen(navController = navController, modifier = modifier)
        }
    }
}