package com.example.sekaipodcast.profile.presentation.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sekaipodcast.Route
import com.example.sekaipodcast.SetStatusBarColor
import com.example.sekaipodcast.profile.presentation.profile.components.CardPodcast
import com.example.sekaipodcast.profile.presentation.profile.components.DialogMutual
import com.example.sekaipodcast.profile.presentation.profile.components.ProfileInfo

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val showDialogFollower = remember { mutableStateOf(false) }
    val showDialogFollowing = remember { mutableStateOf(false) }
    val user = viewModel.user
    val following = viewModel.following
    val followers = viewModel.followers
    val podcasts = viewModel.podcasts

    SetStatusBarColor(color = Color(0xFFFF8673))
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFF8673))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xFFFF8673))
        ){
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
                .background(Color.White)
                .padding(top = 35.dp, start = 15.dp, end = 15.dp),
            contentPadding = PaddingValues(bottom = 35.dp)
        ) {
            item {
                if (user != null) {
                    ProfileInfo(
                        navController = navController,
                        user = user,
                        onLogout = {
                            viewModel.logout()
                        },
                        onOpenDialogFollower = { showDialogFollower.value = true },
                        onOpenDialogFollowing = { showDialogFollowing.value = true }
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    fontSize = 15.sp,
                    text = "Konten Saya",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            if (podcasts != null) {
                items(podcasts.size) {
                    Spacer(modifier = Modifier.height(12.dp))
                    CardPodcast(
                        modifier = Modifier.clickable {
                            navController.navigate(Route.PodcastScreen.route)
                        },
                        podcasts[it],
                        onDelete = {
                            viewModel.deletePodcast(podcasts[it].id)
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        DialogMutual(navController = navController, data = followers,showDialog = showDialogFollower, onConfirm = {  }, onDismiss = { showDialogFollower.value = false }, label = "Followers")
        DialogMutual(navController = navController, data = following, showDialog = showDialogFollowing, onConfirm = {  }, onDismiss = { showDialogFollowing.value = false }, label = "Following")
    }
}