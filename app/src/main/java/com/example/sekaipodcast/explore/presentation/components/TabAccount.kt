package com.example.sekaipodcast.explore.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sekaipodcast.R
import com.example.sekaipodcast.Route

@Composable
fun TabAccount(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Text(
                fontSize = 20.sp,
                text = "Choose a Language",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            Spacer(modifier = Modifier.height(6.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val painter = painterResource(id = R.drawable.spain)
                Image(
                    modifier = Modifier
                        .size(45.dp, 35.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Image(
                    modifier = Modifier
                        .size(45.dp, 35.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Image(
                    modifier = Modifier
                        .size(45.dp, 35.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Image(
                    modifier = Modifier
                        .size(45.dp, 35.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            Text(
                fontSize = 15.sp,
                text = "Recent Searches",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        items(4) {
            Spacer(modifier = Modifier.height(12.dp))
            CardAccount(
                modifier = Modifier.clickable {
                    navController.navigate(Route.UserScreen.route)
                }
            )
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            Text(
                fontSize = 15.sp,
                text = "Top Podcaster",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        items(4) {
            Spacer(modifier = Modifier.height(12.dp))
            CardAccount(
                modifier = Modifier.clickable {
                    navController.navigate(Route.UserScreen.route)
                }
            )
        }
    }
}