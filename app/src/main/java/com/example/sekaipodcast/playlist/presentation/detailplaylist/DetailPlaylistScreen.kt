package com.example.sekaipodcast.playlist.presentation.detailplaylist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.example.sekaipodcast.playlist.presentation.detailplaylist.components.CardPodcast
import com.example.sekaipodcast.playlist.presentation.detailplaylist.components.PlaylistInfo

@Composable
fun DetailPlaylistScreen(
    navController: NavController,
    playlistId: String,
    modifier: Modifier = Modifier,
    viewModel: DetailPlaylistViewModel = hiltViewModel()
) {
    viewModel.getPlaylistDetail(playlistId)

    val response = viewModel.response
    val statusDelete = viewModel.statusDelete

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
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Spacer(modifier = Modifier.width(10.dp))
                response?.data?.let {
                    Text(
                        fontSize = 15.sp,
                        text = it.name,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
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
                response?.data?.let {
                    PlaylistInfo(
                        data = it,
                        onDelete = {
                            viewModel.deletePlaylist(playlistId)
                            navController.navigate(Route.PlaylistScreen.route)
                        }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
            response?.data?.podcasts?.forEachIndexed { index, podcast ->
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                    CardPodcast(navController = navController, podcast = podcast)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}