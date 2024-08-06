package com.example.sekaipodcast.profile.presentation.user.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sekaipodcast.common.Constants
import com.example.sekaipodcast.profile.data.remote.dto.Podcast

@Composable
fun CardPodcast(
    modifier: Modifier = Modifier,
    podcast: Podcast
) {
    Row (
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "${Constants.BASE_URL_DEV}/v1/podcast/thumbnail/${podcast.thumbnailFilename}",
            contentDescription = "logo",
            modifier = Modifier
                .size(110.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Column {
            AsyncImage(
                model = "${Constants.BASE_URL_DEV}/v1/country/logo/${podcast.countrylogo}",
                contentDescription = "logo",
                modifier = Modifier
                    .size(30.dp, 15.dp)
                    .clip(RoundedCornerShape(5.dp)),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.width(160.dp),
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
                    text = "25 Minutes",
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
                    text = "100 like",
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
                    text = "1,000 people listen to this",
                    fontSize = 10.sp
                )
            }
        }
        CircularIcon(
            icon = Icons.Default.PlayArrow,
            contentDescription = "Play",
            size = 40.dp
        )
    }
}

@Composable
fun CircularIcon(
    icon: ImageVector,
    contentDescription: String?,
    size: Dp,
    iconSize: Dp = 25.dp,
    backgroundColor: Color = Color(0xFFFF8673),
    iconColor: Color = Color.White
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = iconColor,
            modifier = Modifier.size(iconSize)
        )
    }
}