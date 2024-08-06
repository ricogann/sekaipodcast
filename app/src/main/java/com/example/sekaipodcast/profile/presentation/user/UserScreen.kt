package com.example.sekaipodcast.profile.presentation.user

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sekaipodcast.Route
import com.example.sekaipodcast.SetStatusBarColor
import com.example.sekaipodcast.profile.presentation.profile.components.UserBottomSheet
import com.example.sekaipodcast.profile.presentation.user.components.CardPodcast
import com.example.sekaipodcast.profile.presentation.user.components.UserInfo

data class BottomSheetsItem(
    val title: String,
    val fontSize: Int,
    val color: Color,
    val fontWeight: FontWeight,
    val action: () -> Unit = { }
)

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    navController: NavController,
    profileId: String,
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = hiltViewModel()
) {
    viewModel.fetchDataUserDetail(profileId)
    viewModel.fetchFollowInfo(profileId)

    val followStatus = viewModel.followStatus
    val dataUser = viewModel.user

    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    val items = listOf(
        BottomSheetsItem(
            title = "Blokir",
            fontSize = 18,
            color = Color.Red,
            fontWeight = FontWeight.Bold,
        ),
        BottomSheetsItem(
            title = "Report",
            fontSize = 18,
            color = Color.Red,
            fontWeight = FontWeight.Bold,
        ),
        BottomSheetsItem(
            title = "Share this profile",
            fontSize = 18,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
        ),
        BottomSheetsItem(
            title = "QR Code",
            fontSize = 18,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
        ),
    )
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFFFFFFFF),
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Hamburger",
                    tint = Color.White,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            isSheetOpen = true
                        }
                )

            }
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
                UserInfo(
                    data = dataUser,
                    statusFollow = followStatus,
                    onFollow = { viewModel.followUser(profileId) },
                    onUnfollow = { viewModel.unfollowUser(profileId) }
                )
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    fontSize = 15.sp,
                    text = "Konten Podcast",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(4) {
                Spacer(modifier = Modifier.height(12.dp))
                CardPodcast(
                    modifier = Modifier.clickable {
                        navController.navigate(Route.PodcastScreen.route)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        UserBottomSheet(sheetState = sheetState, isSheetOpen = isSheetOpen, onDismiss = { isSheetOpen = false }, items = items)
    }
}