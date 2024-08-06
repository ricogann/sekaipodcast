package com.example.sekaipodcast.podcast.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sekaipodcast.ui.theme.SekaipodcastTheme

@Composable
fun AboutPodcast(
    modifier: Modifier = Modifier
) {
    Column  {
        Text(
            fontSize = 15.sp,
            text = "About Podcast",
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            fontSize = 12.sp,
            lineHeight = 15.sp,
            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            color = Color.Black,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AboutPodcastPreview() {
    SekaipodcastTheme {
        AboutPodcast()
    }
}