package com.example.sekaipodcast.explore.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sekaipodcast.R
import com.example.sekaipodcast.home.presentation.components.CircularIcon

@Composable
fun CardPodcast(
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter = painterResource(id = R.drawable.dummy)
        Image(
            modifier = Modifier
                .size(110.dp)
                .clip(RoundedCornerShape(16.dp)),
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column (

        ) {
            val painter = painterResource(id = R.drawable.spain)
            Image(
                modifier = Modifier
                    .size(25.dp, 15.dp)
                    .clip(RoundedCornerShape(5.dp)),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Life in Spain - Part 1",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Rico Putra Anugerah",
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