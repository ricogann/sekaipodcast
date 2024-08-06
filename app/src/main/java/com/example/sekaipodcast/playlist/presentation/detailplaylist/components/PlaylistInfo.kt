package com.example.sekaipodcast.playlist.presentation.detailplaylist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sekaipodcast.playlist.data.remote.dto.DataPlaylistDetail
import com.example.sekaipodcast.ui.theme.SekaipodcastTheme

@Composable
fun PlaylistInfo(
    modifier: Modifier = Modifier,
    data: DataPlaylistDetail,
    onDelete: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Column (
        modifier = modifier.fillMaxWidth()
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                modifier = Modifier
                    .width(250.dp)
            ) {
                Text(
                    fontSize = 20.sp,
                    text = data.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    fontSize = 12.sp,
                    text = data.nameaccount,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    fontSize = 12.sp,
                    lineHeight = 15.sp,
                    text = data.description,
                    color = Color.Black,
                )
            }
            CircularIcon(
                icon = Icons.Default.PlayArrow,
                contentDescription = "Play",
                size = 40.dp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "People",
                tint = Color.Black,
                modifier = Modifier.size(25.dp)
            )
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = "Edit",
                tint = Color.Black,
                modifier = Modifier.size(25.dp)
            )
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Delete",
                tint = Color.Black,
                modifier = Modifier.size(25.dp).clickable {
                    showDialog = true
                }
            )
        }
    }
    DeleteDialog(
        title = "Delete Playlist",
        content = "Are you sure to delete this playlist?",
        button1 = "Delete",
        colorButton1 = Color.Red,
        button2 = "Cancel",
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        onConfirm = {
            onDelete()
            showDialog = false
        }
    )
}