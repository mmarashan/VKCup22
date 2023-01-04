package io.volgadev.sampleapp.feature.questionnaire.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import io.volgadev.core.feature_api.FeatureApi
import io.volgadev.sampleapp.feature.questionnaire.presentation.quiz.QuestionnaireQuizScreen
import io.volgadev.sampleapp.feature.questionnaire.presentation.root.QuestionnaireDemoRouteScreen

interface QuestionnaireApi : FeatureApi {
    val rootRoute: String
}

internal class QuestionnaireImpl : QuestionnaireApi {

    private val quizScreenRoute: String = "questionnaire/quiz"

    override val rootRoute: String = "questionnaire/root"

    override fun registerInGraph(
        navGraphBuilder: NavGraphBuilder, navController: NavHostController, modifier: Modifier
    ) {
        navGraphBuilder.composable(rootRoute) {
            QuestionnaireDemoRouteScreen(
                modifier = modifier,
                onClickToQuiz = {
                    navController.navigate(quizScreenRoute)
                }
            )
        }

        navGraphBuilder.composable(quizScreenRoute) {
            QuestionnaireQuizScreen(navController = navController, modifier = modifier)
        }
    }
}