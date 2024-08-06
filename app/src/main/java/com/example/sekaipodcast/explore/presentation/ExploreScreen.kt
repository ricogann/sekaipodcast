package com.example.sekaipodcast.explore.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sekaipodcast.SetStatusBarColor
import com.example.sekaipodcast.home.presentation.search.components.InputSearch
import com.example.sekaipodcast.explore.presentation.components.TabAccount
import com.example.sekaipodcast.explore.presentation.components.TabPodcast

@Composable
fun ExploreScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    SetStatusBarColor(color = Color.White)
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Podcast", "Account")

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 10.dp, start = 16.dp, end = 16.dp)
    ) {
        InputSearch()
        Spacer(modifier = Modifier.height(10.dp))
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.White,
            modifier = Modifier.fillMaxWidth(),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = Color(0xFFFF8673)
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                val backgroundColor = if (selectedTabIndex == index) Color(0xFFFF8673) else Color.White
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    modifier = Modifier
                        .background(backgroundColor)
                        .padding(vertical = 12.dp)
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Medium,
                            color = if (selectedTabIndex == index) Color.White else Color.Black
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        when (selectedTabIndex) {
            0 -> TabPodcast(navController)
            1 -> TabAccount(navController)
        }
    }
}