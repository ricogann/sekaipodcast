package com.example.sekaipodcast.podcast.presentation.addpodcast

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue

data class FieldInput(
    val label: String,
    val type: String,
    val icon: ImageVector,
    val state: TextFieldValue,
)
