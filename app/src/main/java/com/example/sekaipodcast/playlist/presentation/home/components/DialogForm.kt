package com.example.sekaipodcast.playlist.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogForm(
    title: String,
    button1: String,
    nameValue: TextFieldValue,
    descriptionValue: TextFieldValue,
    onChangeNameValue: (TextFieldValue) -> Unit,
    onChangeDescriptionValue: (TextFieldValue) -> Unit,
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
                Column {
                    Spacer(modifier = Modifier.height(4.dp))
                    val containerColor = Color(0xFFEFEFEF)
                    Text("Name", color = Color.White)
                    Spacer(modifier = Modifier.height(4.dp))
                    TextField(
                        value = nameValue,
                        onValueChange = {
                            onChangeNameValue(it)
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = containerColor,
                            unfocusedContainerColor = containerColor,
                            disabledContainerColor = containerColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedLabelColor = Color.Gray,
                            unfocusedLabelColor = Color.Gray,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        ),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Description", color = Color.White)
                    Spacer(modifier = Modifier.height(4.dp))
                    TextField(
                        value = descriptionValue,
                        onValueChange = { onChangeDescriptionValue(it) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = containerColor,
                            unfocusedContainerColor = containerColor,
                            disabledContainerColor = containerColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedLabelColor = Color.Gray,
                            unfocusedLabelColor = Color.Gray,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        ),
                    )
                }
            },
            confirmButton = {
//                Button(
//                    onClick = { onDismiss() },
//                    colors = ButtonDefaults.buttonColors(containerColor = colorButton1)
//                ) {
//                    Text(button1, color = Color.White)
//                }
                TextButton(onClick = onConfirm) {
                    Text(button2, color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(button1, color = Color.Red)
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