package com.pfv.advancedreminder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pfv.advancedreminder.ui.navigation.routes.Screens
import com.pfv.advancedreminder.ui.screens.add_new_reminder.AddNewReminderScreen
import com.pfv.advancedreminder.ui.screens.home.HomeScreen
import com.pfv.advancedreminder.ui.screens.splash_screen.SplashScreen

@Composable
fun MainNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screens.Splash
    ){

        composable<Screens.Splash> {

            SplashScreen(navController = navController)
        }

        composable<Screens.Home> {

            HomeScreen(navController = navController)
        }

        composable<Screens.AddNewReminder> {

            AddNewReminderScreen(navController = navController)
        }
    }
}