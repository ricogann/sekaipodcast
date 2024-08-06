package com.example.sekaipodcast.playlist.presentation.detailplaylist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.sekaipodcast.R
import com.example.sekaipodcast.Route
import com.example.sekaipodcast.common.Constants
import com.example.sekaipodcast.playlist.data.remote.dto.Podcast
import com.example.sekaipodcast.ui.theme.SekaipodcastTheme


@Composable
fun CardPodcast(
    modifier: Modifier = Modifier,
    navController: NavController,
    podcast: Podcast,
    isPlayShow: Boolean,
    onAddPodcastToPlaylist: () -> Unit
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (isPlayShow) {
                    navController.navigate(Route.PodcastScreen.createRoute(podcast.id))
                } else {
                    onAddPodcastToPlaylist()
                }
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = "${Constants.BASE_URL_DEV}/v1/podcast/thumbnail/${if (isPlayShow) podcast.thumbnail else podcast.thumbnailFilename}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column {
            AsyncImage(
                modifier = Modifier
                    .size(25.dp, 15.dp)
                    .clip(RoundedCornerShape(5.dp)),
                model = "${Constants.BASE_URL_DEV}/v1/country/logo/${podcast.countrylogo}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = if (isPlayShow) Modifier.width(160.dp) else Modifier.width(200.dp),
                text = podcast.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = podcast.name,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row (
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Minutes",
                    tint = Color.Black,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "${podcast.duration} Minutes",
                    fontSize = 10.sp
                )
            }
            Row (
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = "Like",
                    tint = Color.Black,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "${podcast.totallikes} like",
                    fontSize = 10.sp
                )
            }
            Row (
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "People",
                    tint = Color.Black,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "${podcast.totallisten} people listen to this",
                    fontSize = 10.sp
                )
            }
        }
        if (isPlayShow) {
            com.example.sekaipodcast.home.presentation.components.CircularIcon(
                icon = Icons.Default.PlayArrow,
                contentDescription = "Play",
                size = 40.dp
            )
        }
    }
}