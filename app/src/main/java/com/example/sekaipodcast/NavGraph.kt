package com.example.sekaipodcast

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sekaipodcast.auth.presentation.login.LoginScreen
import com.example.sekaipodcast.auth.presentation.register.RegisterScreen
import com.example.sekaipodcast.common.Constants
import com.example.sekaipodcast.explore.presentation.ExploreNavGraph
import com.example.sekaipodcast.home.presentation.HomeNavGraph
import com.example.sekaipodcast.onboarding.presentation.OnBoardingScreen
import com.example.sekaipodcast.onboarding.presentation.OnBoardingViewModel
import com.example.sekaipodcast.player.service.Music
import com.example.sekaipodcast.playlist.presentation.PlaylistNavGraph
import com.example.sekaipodcast.podcast.presentation.addpodcast.AddEditPodcastScreen
import com.example.sekaipodcast.podcast.presentation.home.PodcastScreen
import com.example.sekaipodcast.podcast.presentation.home.PodcastViewModel
import com.example.sekaipodcast.profile.presentation.ProfileNavGraph
import com.example.sekaipodcast.ui.theme.SekaipodcastTheme

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val hasNews: Boolean = false
)

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()


    val items = listOf(
        BottomNavigationItem(
            title = Route.HomeScreen.route,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false
        ),
        BottomNavigationItem(
            title = Route.ExploreScreen.route,
            selectedIcon = Icons.Filled.PlayArrow,
            unselectedIcon = Icons.Outlined.PlayArrow,
            hasNews = false
        ),
        BottomNavigationItem(
            title = Route.AddEditPodcastScreen.route,
            selectedIcon = Icons.Filled.AddCircle,
            unselectedIcon = Icons.Outlined.AddCircle,
            hasNews = false
        ),
        BottomNavigationItem(
            title = Route.PlaylistScreen.route,
            selectedIcon = Icons.Filled.List,
            unselectedIcon = Icons.Outlined.List,
            hasNews = false
        ),
        BottomNavigationItem(
            title = Route.ProfileScreen.route,
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            hasNews = false
        ),
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            if (currentRoute != Route.LoginScreen.route &&
                currentRoute != Route.RegisterScreen.route &&
                currentRoute != Route.OnBoardingScreen.route
                ) {
                Column {
                    Spacer(modifier = Modifier.height(4.dp))
                    NavigationBar (
                        containerColor = Color.White,
                        contentColor = Color.Black,
                    ) {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedItemIndex == index,
                                onClick = {
                                    selectedItemIndex = index
                                    navController.navigate(item.title)
                                },
                                icon = {
                                    BadgedBox(badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if (item.hasNews) {
                                            Badge()
                                        }
                                    }) {
                                        Icon(
                                            imageVector = if (index == selectedItemIndex) {
                                                item.selectedIcon
                                            } else item.unselectedIcon, contentDescription = item.title
                                        )
                                    }
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = if (selectedItemIndex == index) Color(0xFFFF8673) else Color.Transparent,
                                    selectedIconColor = Color.White,
                                    unselectedIconColor = Color.Black
                                ),
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
            }
            composable(Route.LoginScreen.route) {
                LoginScreen(navController)
            }
            composable(Route.RegisterScreen.route) {
                RegisterScreen(navController)
            }
            composable(Route.HomeScreen.route) {
                HomeNavGraph(startDestination = Route.HomeScreen.route)
            }
            composable(Route.AddEditPodcastScreen.route) {
                AddEditPodcastScreen()
            }
            composable(Route.PlaylistScreen.route) {
                PlaylistNavGraph(startDestination = Route.PlaylistScreen.route)
            }
            composable(Route.ProfileScreen.route) {
                ProfileNavGraph(startDestination = Route.ProfileScreen.route)
            }
            composable(Route.ExploreScreen.route) {
                ExploreNavGraph(startDestination = Route.ExploreScreen.route)
            }
        }
    }
}