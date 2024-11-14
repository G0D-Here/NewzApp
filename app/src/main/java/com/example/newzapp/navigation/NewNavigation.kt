package com.example.newzapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newzapp.screens.mainscreencomponents.MainScreen

@Composable
fun NewsNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AllScreens.MainScreen.name) {
        composable(AllScreens.MainScreen.name) { MainScreen() }
    }
}
