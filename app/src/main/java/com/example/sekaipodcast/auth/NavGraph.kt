package com.example.sekaipodcast.auth

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sekaipodcast.Route
import com.example.sekaipodcast.auth.presentation.login.LoginScreen
import com.example.sekaipodcast.auth.presentation.register.RegisterScreen
import com.example.sekaipodcast.home.presentation.HomeScreen
import com.example.sekaipodcast.onboarding.presentation.OnBoardingScreen
import com.example.sekaipodcast.onboarding.presentation.OnBoardingViewModel
import com.example.sekaipodcast.podcast.presentation.home.PodcastScreen

@Composable
fun AuthNavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Route.LoginScreen.route) {
            LoginScreen(navController)
        }
//        composable(Route.HomeScreen.route) {
//            HomeScreen(navController)
//        }
        composable(Route.RegisterScreen.route) {
            RegisterScreen(navController)
        }
    }
}