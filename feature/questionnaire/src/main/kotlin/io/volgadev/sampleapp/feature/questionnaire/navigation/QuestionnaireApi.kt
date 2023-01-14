package io.volgadev.sampleapp.feature.questionnaire.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import io.volgadev.core.feature_api.FeatureApi
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsdragging.GapDraggingScreen
import io.volgadev.sampleapp.feature.questionnaire.presentation.gapsfilling.GapFillingScreen
import io.volgadev.sampleapp.feature.questionnaire.presentation.pairmapping.PairMappingScreen
import io.volgadev.sampleapp.feature.questionnaire.presentation.quiz.QuestionnaireQuizScreen
import io.volgadev.sampleapp.feature.questionnaire.presentation.rating.QuestionnaireRatingScreen
import io.volgadev.sampleapp.feature.questionnaire.presentation.root.QuestionnaireDemoRouteScreen

interface QuestionnaireApi : FeatureApi {
    val rootRoute: String
}

internal class QuestionnaireImpl : QuestionnaireApi {

    private val quizScreenRoute: String = "questionnaire/quiz"
    private val ratingScreenRoute: String = "questionnaire/rating"
    private val gapFillingScreenRoute: String = "questionnaire/gapFilling"
    private val gapDraggingScreenRoute: String = "questionnaire/gapDragging"
    private val pairMappingScreenRoute: String = "questionnaire/pairMapping"

    override val rootRoute: String = "questionnaire/root"

    override fun registerInGraph(
        navGraphBuilder: NavGraphBuilder, navController: NavHostController, modifier: Modifier
    ) {
        navGraphBuilder.composable(rootRoute) {
            QuestionnaireDemoRouteScreen(modifier = modifier, onClickToQuiz = {
                navController.navigate(quizScreenRoute)
            }, onClickToRating = {
                navController.navigate(ratingScreenRoute)
            }, onClickToGapFilling = {
                navController.navigate(gapFillingScreenRoute)
            }, onClickToGapDragging = {
                navController.navigate(gapDraggingScreenRoute)
            }, onClickToPairMapping = {
                navController.navigate(pairMappingScreenRoute)
            })
        }

        navGraphBuilder.composable(quizScreenRoute) {
            QuestionnaireQuizScreen(navController = navController, modifier = modifier)
        }
        navGraphBuilder.composable(ratingScreenRoute) {
            QuestionnaireRatingScreen(navController = navController, modifier = modifier)
        }
        navGraphBuilder.composable(gapFillingScreenRoute) {
            GapFillingScreen(navController = navController, modifier = modifier)
        }
        navGraphBuilder.composable(gapDraggingScreenRoute) {
            GapDraggingScreen(navController = navController, modifier = modifier)
        }
        navGraphBuilder.composable(pairMappingScreenRoute) {
            PairMappingScreen(navController = navController, modifier = modifier)
        }
    }
}