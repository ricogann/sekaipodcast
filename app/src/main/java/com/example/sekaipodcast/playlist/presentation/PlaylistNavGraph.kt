package com.example.sekaipodcast.playlist.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sekaipodcast.Route
import com.example.sekaipodcast.playlist.presentation.detailplaylist.DetailPlaylistScreen
import com.example.sekaipodcast.playlist.presentation.home.HomePlaylistScreen
import com.example.sekaipodcast.podcast.presentation.home.PodcastScreen

@Composable
fun PlaylistNavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Route.PlaylistScreen.route) {
            HomePlaylistScreen(navController)
        }
        composable(Route.PlaylistDetailScreen.route) { backStackEntry ->
            val playlistId = backStackEntry.arguments?.getString("playlistId") ?: return@composable
            DetailPlaylistScreen(navController, playlistId)
        }
        composable(Route.PodcastScreen.route) {
//            PodcastScreen(navController)
        }
    }
}