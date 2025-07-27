package com.somnathdey.superapp.presentation.screens

sealed class Screen(val route: String) {

    data object HomeScreen : Screen(route = Route.HOME_SCREEN.name)
    data object WealthHomeScreen : Screen(route = Route.WEALTH_HOME_SCREEN.name)

    data object CoinDetailsScreen : Screen(route = Route.COIN_DETAILS_SCREEN.name)
}

enum class Route {
    HOME_SCREEN,
    WEALTH_HOME_SCREEN,
    COIN_DETAILS_SCREEN
}

enum class ScreenArguments(name: String) {
    COIN_ID("coinId")
}