package com.example.sekaipodcast.playlist.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sekaipodcast.Route
import com.example.sekaipodcast.playlist.data.remote.dto.Data
import com.example.sekaipodcast.playlist.domain.model.CreatePlaylistRequest
import com.example.sekaipodcast.playlist.presentation.home.components.CardPlaylist
import com.example.sekaipodcast.playlist.presentation.home.components.DialogForm
import com.example.sekaipodcast.ui.theme.SekaipodcastTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePlaylistScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomePlaylistViewModel = hiltViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    var description by remember { mutableStateOf(TextFieldValue()) }
    var name by remember { mutableStateOf(TextFieldValue()) }
    val accountState = viewModel.id

    var nameError by remember { mutableStateOf<String?>(null) }
    var descriptionError by remember { mutableStateOf<String?>(null) }

    val playlists = viewModel.playlists

    fun validateFields(): Boolean {
        var isValid = true
        nameError = if (name.text.isBlank()) {
            isValid = false
            "Name is required"
        } else null

        descriptionError = if (description.text.isBlank()) {
            isValid = false
            "Description is required"
        } else null

        return isValid
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 25.dp, start = 16.dp, end = 16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    fontSize = 18.sp,
                    text = "Playlist Kamu",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Hamburger",
                    tint = Color(0xFFFF8673),
                    modifier = Modifier.size(25.dp)
                )

            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    showDialog = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF8673),
                ),
            ) {
                Text(
                    fontSize = 10.sp,
                    text = "TAMBAH PLAYLIST",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        playlists?.let {
            items(it) { playlist ->
                CardPlaylist(navController = navController, playlist = playlist)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
    DialogForm(
        title = "Create Playlist",
        nameValue = name,
        descriptionValue = description,
        onChangeNameValue = { name = it },
        onChangeDescriptionValue = { description = it },
        button1 = "Cancel",
        button2 = "Create",
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        onConfirm = {
            if (validateFields()) {
                viewModel.createPlaylist(
                    CreatePlaylistRequest(
                        account = accountState,
                        name = name.text,
                        description = description.text
                    )
                )
                showDialog = false
            }
        }
    )
}