package com.example.sekaipodcast.home.presentation.search.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sekaipodcast.R
import com.example.sekaipodcast.ui.theme.SekaipodcastTheme

@Composable
fun CardAccount(
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = "profile account",
            tint = Color.Black,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Text(
                    fontSize = 18.sp,
                    text = "Rico Putra Anugerah",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                val painter = painterResource(id = R.drawable.spain)
                Image(
                    modifier = Modifier
                        .size(25.dp, 15.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                fontSize = 12.sp,
                lineHeight = 15.sp,
                text = "20 Podcast, 10.000 Minutes Listener",
                color = Color.Black,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardAccountPreview() {
    SekaipodcastTheme {
        CardAccount()
    }
}