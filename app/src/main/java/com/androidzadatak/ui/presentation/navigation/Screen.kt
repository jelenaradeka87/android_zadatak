package com.androidzadatak.ui.presentation.navigation

sealed class Screen(val route: String) {

    data object HomeScreen : Screen("home_screen")
}