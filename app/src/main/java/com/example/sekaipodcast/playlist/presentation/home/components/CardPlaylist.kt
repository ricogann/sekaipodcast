package com.example.sekaipodcast.playlist.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.sekaipodcast.Route
import com.example.sekaipodcast.common.Constants
import com.example.sekaipodcast.playlist.data.remote.dto.Data

@Composable
fun CardPlaylist(
    navController: NavController,
    playlist: Data,
) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable {
            navController.navigate(Route.PlaylistDetailScreen.createRoute(playlist.id))
        },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ThumbnailOrPlaceholder(playlist.thumbnail, 0)
                ThumbnailOrPlaceholder(playlist.thumbnail, 1)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ThumbnailOrPlaceholder(playlist.thumbnail, 2)
                ThumbnailOrPlaceholder(playlist.thumbnail, 3)
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                fontSize = 15.sp,
                text = playlist.name,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                fontSize = 10.sp,
                text = playlist.description,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            Text(
                fontSize = 10.sp,
                text = "${playlist.thumbnail?.size ?: 0} Podcast",
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            Text(
                fontSize = 10.sp,
                text = "See more",
                style = TextStyle(
                    textDecoration = TextDecoration.Underline
                ),
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ThumbnailOrPlaceholder(thumbnailList: List<String>?, index: Int) {
    if (thumbnailList != null && thumbnailList.size > index) {
        AsyncImage(
            modifier = Modifier
                .size(40.dp),
            model = "${Constants.BASE_URL_DEV}/v1/podcast/thumbnail/${thumbnailList[index]}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    } else {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFEFEFEF))
        )
    }
}