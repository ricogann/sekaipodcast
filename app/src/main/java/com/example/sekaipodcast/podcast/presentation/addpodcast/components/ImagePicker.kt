package com.example.sekaipodcast.podcast.presentation.addpodcast.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ImagePicker(
    imageUri: Uri?,
    errorMessage: String? = null,
    onImageUriChanged: (Uri?) -> Unit
) {
    var isError by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { onImageUriChanged(it) }
    }

    LaunchedEffect(errorMessage) {
        isError = errorMessage != null
    }

    Column {
        Text(
            text = "Thumbnail",
            fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        if (imageUri == null) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(2.dp, color = if (isError && errorMessage != null) Color.Red else Color.Transparent, RoundedCornerShape(16.dp)),
                onClick = { launcher.launch("image/*") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEFEFEF),
                    contentColor = Color.Gray
                )
            ) {
                Text("Select a Thumbnail")
            }
            if (isError && errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFEFEFEF))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = imageUri.path ?: "Selected Image",
                    modifier = Modifier.weight(1f),
                    fontSize = 15.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Remove Image",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onImageUriChanged(null)
                        },
                    tint = Color.Gray
                )
            }
        }
    }
}
