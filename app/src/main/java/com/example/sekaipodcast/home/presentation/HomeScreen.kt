package com.example.sekaipodcast.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sekaipodcast.Route
import com.example.sekaipodcast.SetStatusBarColor
import com.example.sekaipodcast.home.presentation.components.CardPodcast
import com.example.sekaipodcast.ui.theme.SekaipodcastTheme

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    SetStatusBarColor(color = Color(0xFFFF8673))
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFF8673))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(Color(0xFFFF8673))
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column (
                    modifier = Modifier
                        .height(100.dp)
                        .width(230.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text (
                        text = "Welcome back, Rico!",
                        fontSize = 15.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text (
                        text = "You already listen 5 podcast! Keep it up!",
                        fontSize = 15.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Row (
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                              navController.navigate(Route.SearchScreen.route)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                        ),
                        shape = CircleShape,
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.size(25.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                        ),
                        shape = CircleShape,
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.size(25.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Message",
                            tint = Color.White,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
                .background(Color.White)
                .padding(top = 35.dp, start = 15.dp, end = 15.dp),
            contentPadding = PaddingValues(bottom = 35.dp)
        ) {
            item {
                Text(
                    fontSize = 20.sp,
                    text = "Podcast for you",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            items(4) { index -> // Adjust this count based on your needs
                Spacer(modifier = Modifier.height(12.dp))
                CardPodcast(modifier = Modifier.clickable {
                    navController.navigate(Route.PodcastScreen.route)
                })
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
            item {
                Text(
                    fontSize = 20.sp,
                    text = "You like a podcast similar to this",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            items(4) { index -> // Adjust this count based on your needs
                Spacer(modifier = Modifier.height(12.dp))
                CardPodcast()
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
