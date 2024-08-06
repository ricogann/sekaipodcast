package com.example.sekaipodcast.podcast.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sekaipodcast.R
import com.example.sekaipodcast.SetStatusBarColor
import com.example.sekaipodcast.player.service.Music
import com.example.sekaipodcast.player.service.PlayerHandlers
import com.example.sekaipodcast.podcast.presentation.home.components.AboutPodcast
import com.example.sekaipodcast.podcast.presentation.home.components.CardComment
import com.example.sekaipodcast.podcast.presentation.home.components.Podcast
import com.example.sekaipodcast.ui.theme.SekaipodcastTheme

@Composable
fun PodcastScreen(
    modifier: Modifier = Modifier,
    valueSlider: Float,
    onValueSliderChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit,
    songDuration: Float,
    onPlayPause: () -> Unit,
    isPlaying: Boolean,
    onDismiss: () -> Unit
) {
    SetStatusBarColor(color = Color.White)

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
                onClick = { onDismiss() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Back",
                    tint = Color(0xFFFF8673),
                    modifier = Modifier.size(25.dp)
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                fontSize = 15.sp,
                text = "Rico Putra Anugerah",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
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
                    Podcast(
                        modifier = Modifier.clickable {

                        },
                        valueSlider = valueSlider,
                        onValueSliderChange = { newValue ->
                            onValueSliderChange(newValue)
                        },
                        onValueChangeFinished = {
                            onValueChangeFinished()
                        },
                        songDuration = songDuration,
                        onPlayPause = { onPlayPause() },
                        isPlaying = isPlaying
                    )
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