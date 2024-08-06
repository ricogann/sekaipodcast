package com.example.sekaipodcast.profile.presentation.profile.components

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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sekaipodcast.R
import com.example.sekaipodcast.common.Constants
import com.example.sekaipodcast.profile.data.remote.dto.Podcast
import com.example.sekaipodcast.ui.theme.SekaipodcastTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardPodcast(
    modifier: Modifier = Modifier,
    podcast: Podcast,
    onDelete: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

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
                    .size(30.dp, 20.dp)
                    .clip(RoundedCornerShape(5.dp)),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = podcast.title,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            Text(
                text = podcast.name,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
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
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp).clickable {
                        showDialog = true
                    }
                )
            }
        }
        CircularIcon(
            icon = Icons.Default.PlayArrow,
            contentDescription = "Play",
            size = 40.dp
        )
    }
    SampleDialog(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        title = "Delete Podcast",
        content = "Are you sure to delete this podcast?",
        button1 = "Delete",
        button2 = "Cancel",
        colorButton1 = Color.Red,
        onConfirm = {
            onDelete()
            showDialog = false
        }
    )
}