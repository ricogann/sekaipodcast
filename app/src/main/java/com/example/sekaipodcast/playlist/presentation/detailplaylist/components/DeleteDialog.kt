package com.example.sekaipodcast.playlist.presentation.detailplaylist.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties

@Composable
fun DeleteDialog(
    title: String,
    content: String,
    button1: String,
    colorButton1: Color,
    button2: String,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = title,
                    color = Color.White
                )
            },
            text = {
                Text(
                    text = content,
                    color = Color.White
                )
            },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(button1, color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(button2, color = Color.White)
                }
            },
            properties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            ),
            containerColor = Color(0xFFFF8673),
        )
    }
}