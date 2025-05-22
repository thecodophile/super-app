package com.somnathdey.superapp.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.somnathdey.superapp.presentation.screens.home.HomeScreen
import com.somnathdey.wealth.presentation.screens.WealthHomeScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(navController, startDestination = Screen.HomeScreen.route) {
            composable(route = Screen.HomeScreen.route) {
                HomeScreen(
                    onClickPrimaryButton = {

                    },
                    onClickWealthBanner = {
                        navController.navigate(Screen.WealthHomeScreen.route)
                    }
                )
            }

            composable(route = Screen.WealthHomeScreen.route) {
                WealthHomeScreen(
                    onClickPrimaryButton = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}