package com.example.sekaipodcast.profile.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
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
import coil.compose.AsyncImage
import com.example.sekaipodcast.R
import com.example.sekaipodcast.Route
import com.example.sekaipodcast.common.Constants
import com.example.sekaipodcast.profile.data.remote.dto.DataMutual

@Composable
fun CardAccount(
    navController: NavController,
    modifier: Modifier = Modifier,
    data: DataMutual
) {
    Row (
        modifier = modifier.fillMaxWidth().clickable {
             navController.navigate(Route.UserScreen.createRoute(data.id))
        },
        verticalAlignment = Alignment.CenterVertically
    ){
        if (data.profile != null) {
            AsyncImage(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                model = "${Constants.BASE_URL_DEV}/v1/profile/avatar/${data.profile}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }else {
            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = "profile account",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Column {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Text(
                    fontSize = 15.sp,
                    text = data.name,
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
        }
    }
}