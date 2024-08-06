package com.example.sekaipodcast.onboarding.presentation.components

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sekaipodcast.onboarding.Page
import com.example.sekaipodcast.onboarding.pages
import com.example.sekaipodcast.ui.theme.SekaipodcastTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page,
    pageIndex: Int
) {
    val alignment = if (pageIndex == 0) Alignment.CenterHorizontally else Alignment.Start
    Column (modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.align(alignment),
            fontSize = 35.sp,
            text = page.title,
            color = Color.White,
            maxLines = 3,
            style = TextStyle(lineHeight = 36.sp),
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            modifier = Modifier.align(alignment),
            fontSize = 15.sp,
            text = page.subtitle,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFF8673)
@Composable
fun OnBoardingPagePreview() {
    SekaipodcastTheme {
        OnBoardingPage(page = pages[0], pageIndex = 0)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFF8673)
@Composable
fun OnBoardingPagePreview2() {
    SekaipodcastTheme {
        OnBoardingPage(page = pages[1], pageIndex = 1)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFF8673)
@Composable
fun OnBoardingPagePreview3() {
    SekaipodcastTheme {
        OnBoardingPage(page = pages[2], pageIndex = 2)
    }
}