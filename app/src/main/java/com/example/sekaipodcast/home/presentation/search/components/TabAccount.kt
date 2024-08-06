package com.example.sekaipodcast.home.presentation.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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