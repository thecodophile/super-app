package com.somnathdey.superapp.presentation.screens

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.somnathdey.superapp.presentation.screens.home.HomeScreen
import com.somnathdey.utilities.logging.AppLogger
import com.somnathdey.wealth.presentation.screens.details.CoinDetailsScreen
import com.somnathdey.wealth.presentation.screens.wealthHome.WealthHomeScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(navController, startDestination = Screen.HomeScreen.route) {
            composable(
                route = Screen.HomeScreen.route,
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )
                }) {
                HomeScreen(
                    onClickPrimaryButton = {

                    },
                    onClickWealthBanner = {
                        navController.navigate(Screen.WealthHomeScreen.route)
                    }
                )
            }

            composable(
                route = Screen.WealthHomeScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(500)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )
                }
            ) {
                WealthHomeScreen(
                    onClickPrimaryButton = {
                        navController.popBackStack()
                    },
                    coinItemClicked = { coinId ->
                        navController.navigate(Screen.CoinDetailsScreen.route + "/$coinId")
                    }
                )
            }

            composable(
                route = "${Screen.CoinDetailsScreen.route}/{${ScreenArguments.COIN_ID}}",
                arguments = listOf(
                    navArgument(name = "${ScreenArguments.COIN_ID}") { type = NavType.StringType }
                ),
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(500)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )
                }
            ) {
                it?.arguments?.getString("${ScreenArguments.COIN_ID}")?.also { coinId ->
                    AppLogger.d(message = "CoinId in AppNavGraph--> $coinId")
                    CoinDetailsScreen(
                        coinId,
                        onClickPrimaryButton = {
                            navController.popBackStack()
                        },
                    )
                }
            }

        }
    }
}