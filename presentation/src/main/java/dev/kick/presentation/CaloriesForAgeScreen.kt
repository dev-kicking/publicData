package dev.kick.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.kick.presentation.detail.CaloriesForAgeDetailScreen
import dev.kick.presentation.list.CaloriesForAgeListScreen
import dev.kick.presentation.navigation.CaloriesForAgeNavigation

@Composable
fun CaloriesForAgeScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CaloriesForAgeNavigation.ListScreen,
    ) {
        composable<CaloriesForAgeNavigation.ListScreen> {
            CaloriesForAgeListScreen {
                navController.navigate(CaloriesForAgeNavigation.DetailScreen(it))
            }
        }

        composable<CaloriesForAgeNavigation.DetailScreen>(
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(AnimationConstants.DefaultDurationMillis)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(AnimationConstants.DefaultDurationMillis)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(AnimationConstants.DefaultDurationMillis)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(AnimationConstants.DefaultDurationMillis)
                )
            }
        ) {
            CaloriesForAgeDetailScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}