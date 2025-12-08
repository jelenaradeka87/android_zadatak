package com.androidzadatak.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.androidzadatak.ui.presentation.scenes.HomeScreen

@Composable
fun NavGraph(navController: NavHostController,    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {

        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }
    }
}