package com.example.sekaipodcast.auth.presentation.register.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GreetingsText(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    fontSizeTitle: Int,
    fontSizeSubtitle: Int,
    alignment: Alignment.Horizontal,
    color: Color
) {
    Column (
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.align(alignment),
            fontSize = fontSizeTitle.sp,
            text = title,
            color = color,
            maxLines = 3,
            style = TextStyle(lineHeight = 36.sp),
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            modifier = Modifier.align(alignment),
            fontSize = fontSizeSubtitle.sp,
            text = subtitle,
            color = color,
            fontWeight = FontWeight.Bold
        )
    }
}