package com.codebaron.filmworld.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codebaron.filmworld.screens.SplashScreen

@Composable
fun IntroStageNavigationController() {
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = Destinations.SPLASH_SCREEN.name
    ) {
        composable(Destinations.SPLASH_SCREEN.name) {
            SplashScreen()
        }
        composable(Destinations.OPTION_SCREEN.name) {}
    }
}

@Composable
fun BottomNavigationStageController() {
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = Destinations.MOVIES_HOME_SCREEN.name
    ) {
        composable(Destinations.MOVIES_HOME_SCREEN.name) {}
    }
}