package com.androidzadatak.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.androidzadatak.ui.presentation.MainViewModel
import com.androidzadatak.ui.presentation.MainViewModelFactory
import com.androidzadatak.ui.presentation.scenes.HomeScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
    ) {

        composable(Screen.HomeScreen.route) {
            val context = LocalContext.current
            val viewModel: MainViewModel = viewModel(
                factory = MainViewModelFactory(context)
            )
            HomeScreen(viewModel)
        }
    }
}