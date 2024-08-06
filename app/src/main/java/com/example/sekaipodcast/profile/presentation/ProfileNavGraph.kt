package com.example.sekaipodcast.profile.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sekaipodcast.Route
import com.example.sekaipodcast.auth.presentation.login.LoginScreen
import com.example.sekaipodcast.podcast.presentation.home.PodcastScreen
import com.example.sekaipodcast.profile.presentation.editprofile.EditProfileScreen
import com.example.sekaipodcast.profile.presentation.profile.ProfileScreen
import com.example.sekaipodcast.profile.presentation.user.UserScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileNavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Route.ProfileScreen.route) {
            ProfileScreen(navController)
        }
        composable(Route.PodcastScreen.route) { backStackEntry ->
            val podcastId = backStackEntry.arguments?.getString("podcastId") ?: return@composable
            PodcastScreen(navController, podcastId)
        }
        composable(Route.UserScreen.route) { backStackEntry ->
            val profileId = backStackEntry.arguments?.getString("profileId") ?: return@composable
            UserScreen(navController, profileId)
        }
        composable(Route.EditProfileScreen.route) { backStackEntry ->
            val profileId = backStackEntry.arguments?.getString("profileId") ?: return@composable
            EditProfileScreen(navController)
        }
    }
}