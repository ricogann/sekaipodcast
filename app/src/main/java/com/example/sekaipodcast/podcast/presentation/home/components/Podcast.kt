package com.example.sekaipodcast.podcast.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sekaipodcast.R
import com.example.sekaipodcast.common.Constants
import com.example.sekaipodcast.ui.theme.SekaipodcastTheme

@Composable
fun Podcast(
    title: String,
    thumbnail: String,
    name: String,
    like: Boolean,
    modifier: Modifier = Modifier,
    valueSlider: Float,
    onValueSliderChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit,
    onLike: () -> Unit,
    onUnlike: () -> Unit,
    songDuration: Float,
    onPlayPause: () -> Unit,
    isPlaying: Boolean
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )  {
            AsyncImage(
                model = "${Constants.BASE_URL_DEV}/v1/podcast/thumbnail/${thumbnail}",
                contentDescription = "logo",
                modifier = Modifier
                    .size(360.dp)
                    .clip(RoundedCornerShape(25.dp))
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        modifier = Modifier.width(250.dp),
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        fontSize = 15.sp,
                        text = name,
                        color = Color.Black,
                    )
                }
                Row (
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = if (like) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (like) Color.Red else Color.Black,
                        modifier = Modifier.size(30.dp).clickable {
                            if (like) {
                                onUnlike()
                            }else {
                                onLike()
                            }
                        }
                    )
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Like",
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            TrackSlider(
                value = valueSlider,
                onValueChange = { newValue ->
                    onValueSliderChange(newValue)
                },
                onValueChangeFinished = {
                    onValueChangeFinished()
                },
                songDuration = songDuration
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(45.dp),
                    painter = painterResource(id = R.drawable.previous),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    modifier = Modifier.size(45.dp),
                    painter = painterResource(id = R.drawable.rewind),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = if (isPlaying) Icons.Default.PlayArrow else Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    tint = Color.Black,
                    modifier = Modifier.size(40.dp).clickable {
                        onPlayPause()
                    }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    modifier = Modifier.size(45.dp),
                    painter = painterResource(id = R.drawable.fast_forward),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    modifier = Modifier.size(45.dp),
                    painter = painterResource(id = R.drawable.next),
                    contentDescription = ""
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit,
    songDuration: Float
) {
    val fraction = if (songDuration > 0) value / songDuration else 0f

    Slider(
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        onValueChangeFinished = {
            onValueChangeFinished()
        },
        valueRange = 0f..songDuration,
        colors = SliderDefaults.colors(
            thumbColor = Color(0xFFFF8673),
            activeTrackColor = Color.Transparent,
            inactiveTrackColor = Color.Transparent
        ),
        track = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .border(1.dp, Color(0xFFFF8673), shape = RoundedCornerShape(8.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction)
                        .fillMaxHeight()
                        .background(Color(0xFFFF8673), shape = RoundedCornerShape(8.dp))
                )
            }
        },
    )
}
