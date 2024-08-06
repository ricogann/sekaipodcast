package com.example.sekaipodcast.profile.presentation.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties

@Composable
fun SampleDialog(
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
                Text("Are you sure to delete this playlist?")
            },
            confirmButton = {
//                Button(
//                    onClick = { onDismiss() },
//                    colors = ButtonDefaults.buttonColors(containerColor = colorButton1)
//                ) {
//                    Text(button1, color = Color.White)
//                }
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
