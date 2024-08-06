package com.example.sekaipodcast.podcast.presentation.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sekaipodcast.R
import com.example.sekaipodcast.SetStatusBarColor
import com.example.sekaipodcast.common.Constants
import com.example.sekaipodcast.player.service.Music
import com.example.sekaipodcast.podcast.presentation.home.components.AboutPodcast
import com.example.sekaipodcast.podcast.presentation.home.components.CardComment
import com.example.sekaipodcast.podcast.presentation.home.components.Podcast

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PodcastScreen(
    navController: NavController,
    podcastId: String,
    modifier: Modifier = Modifier,
    viewModel: PodcastViewModel = hiltViewModel()
) {
    viewModel.getDetailPodcast(podcastId)
    SetStatusBarColor(color = Color.White)

    val podcast = viewModel.podcast
    if (podcast != null) {
        val playlist = listOf(
            Music(
                name = podcast.title,
                artist = podcast.name,
                music = R.raw.master_of_puppets,
                url = "${Constants.BASE_URL_DEV}/v1/podcast/file/${podcast.podcastFilename}"
            )
        )
        viewModel.initPlayer(playlist)
        val pagerState = rememberPagerState(pageCount = { playlist.count() })

        LaunchedEffect(pagerState.currentPage) {
            viewModel.onPageChange(pagerState.currentPage)
        }
    }
    val like = viewModel.statusLike

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFFFF8673),
                    modifier = Modifier.size(25.dp).clickable {
                        navController.popBackStack()
                    }
                )
            }
            if (podcast != null) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 15.sp,
                    text = podcast.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            IconButton(
                onClick = {  },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Hamburger",
                    tint = Color(0xFFFF8673),
                    modifier = Modifier.size(25.dp)
                )
            }
        }

        Box(modifier = Modifier.padding(top = 10.dp, start = 16.dp, end = 16.dp)) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                item {
                    if (podcast != null) {
                        Podcast(
                            title = podcast.title,
                            thumbnail = podcast.thumbnailFilename,
                            name = podcast.name,
                            like = like,
                            valueSlider = viewModel.sliderPosition.toFloat(),
                            onValueSliderChange = { newValue ->
                                viewModel.seekTo(newValue.toLong())
                            },
                            onValueChangeFinished = {
                                viewModel.seekTo(viewModel.sliderPosition)
                            },
                            onLike = {
                                viewModel.likePodcast(podcastId)
                            },
                            onUnlike = {
                                viewModel.unlikePodcast(podcastId)
                            },
                            songDuration = viewModel.totalDuration.toFloat(),
                            onPlayPause = { viewModel.playPause() },
                            isPlaying = viewModel.isPlaying
                        )
                    }
                    SpacerVertical(30.dp)
                    AboutPodcast()
                    SpacerVertical(20.dp)
                }
                items(4) {
                    CardComment()
                }
            }
        }
    }
}

@Composable
fun SpacerVertical(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}