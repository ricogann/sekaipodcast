package com.example.sekaipodcast.home.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sekaipodcast.Route
import com.example.sekaipodcast.home.presentation.search.SearchScreen
import com.example.sekaipodcast.podcast.presentation.home.PodcastScreen
import com.example.sekaipodcast.profile.presentation.user.UserScreen

@Composable
fun HomeNavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Route.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(Route.SearchScreen.route) {
            SearchScreen(navController)
        }
        composable(Route.PodcastScreen.route) { backStackEntry ->
            val podcastId = backStackEntry.arguments?.getString("podcastId") ?: return@composable
            PodcastScreen(navController, podcastId)
        }
//        composable(Route.UserScreen.route) {
//            UserScreen(navController)
//        }
    }
}