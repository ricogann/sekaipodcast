package com.example.sekaipodcast.onboarding.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sekaipodcast.onboarding.presentation.components.OnBoardingButton
import com.example.sekaipodcast.onboarding.presentation.components.OnBoardingPage
import com.example.sekaipodcast.onboarding.presentation.components.PageIndicator
import com.example.sekaipodcast.onboarding.pages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF8673))
            .padding(14.dp),
        contentAlignment = Alignment.Center
    ) {
        val pagerState = rememberPagerState (initialPage = 0) {
            pages.size
        }

        HorizontalPager(state = pagerState) { index ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.weight(1f))

                OnBoardingPage(page = pages[index], pageIndex = index)

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val scope = rememberCoroutineScope()

                    if (index != 0) {
                        OnBoardingButton(
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        index - 1
                                    )
                                }
                            },
                            icon = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                    PageIndicator(pageSize = pages.size, selectedPage = index)
                    OnBoardingButton(
                        onClick = {
                            scope.launch {
                                if (pagerState.currentPage == 2) {
                                    event(OnBoardingEvent.SaveAppEntry)
                                }else {
                                    pagerState.animateScrollToPage(
                                        pagerState.currentPage + 1
                                    )
                                }
                            }
                        },
                        icon = Icons.Default.ArrowForward,
                        contentDescription = "Next",
                    )
                }
            }
        }
    }
}
