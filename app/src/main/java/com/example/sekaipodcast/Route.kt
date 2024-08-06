package com.example.sekaipodcast

sealed class Route(
    val route: String
) {

    object OnBoardingScreen: Route(route = "onboardingScreen")
    object RegisterScreen: Route(route = "registerScreen")
    object LoginScreen: Route(route = "loginScreen")
    // On Home Screen
    object HomeScreen: Route(route = "homeScreen")

    object SearchScreen: Route(route = "searchScreen")
    // On Playlist Screen
    object PlaylistScreen: Route(route = "playlistScreen")

    object PlaylistDetailScreen: Route(route = "playlistDetailScreen/{playlistId}") {
        fun createRoute(playlistId: String) = "playlistDetailScreen/$playlistId"
    }

    // On Profile Package
    object UserScreen: Route(route = "userScreen/{profileId}") {
        fun createRoute(profileId: String) = "userScreen/$profileId"
    }
    object ProfileScreen: Route(route = "profileScreen")
    object EditProfileScreen: Route(route = "editProfileScreen/{profileId}") {
        fun createRoute(profileId: String) = "editProfileScreen/$profileId"
    }

    // On Explore Screen
    object ExploreScreen: Route(route = "exploreScreen")

    // On Podcast Screen
    object PodcastScreen: Route(route = "podcastScreen/{podcastId}") {
        fun createRoute(podcastId: String) = "podcastScreen/$podcastId"
    }
    object AddEditPodcastScreen: Route(route = "addEditPodcastScreen")
}
