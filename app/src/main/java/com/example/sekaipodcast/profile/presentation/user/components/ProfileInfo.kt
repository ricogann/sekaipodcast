package com.example.sekaipodcast.profile.presentation.user.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import coil.compose.AsyncImage
import com.example.sekaipodcast.R
import com.example.sekaipodcast.common.Constants
import com.example.sekaipodcast.profile.data.remote.dto.DataUser
import com.example.sekaipodcast.ui.theme.SekaipodcastTheme

@Composable
fun UserInfo(
    data: DataUser?,
    modifier: Modifier = Modifier,
    statusFollow: Boolean,
    onFollow: () -> Unit,
    onUnfollow: () -> Unit,
) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.width(250.dp)
                ) {
                    data?.let {
                        Text(
                            fontSize = 20.sp,
                            text = it.name,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    data?.let {
                        Text(
                            fontSize = 12.sp,
                            text = it.email,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            fontSize = 15.sp,
                            text = if (data?.followers ?: null != null) data?.followers?.size.toString() + " Followers" else "0 Followers",
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            fontSize = 15.sp,
                            text = data?.following?.size.toString() + " Following",
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        val painter = painterResource(id = R.drawable.spain)
                        Image(
                            modifier = Modifier
                                .size(30.dp, 20.dp)
                                .clip(RoundedCornerShape(5.dp)),
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Forward",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                        Image(
                            modifier = Modifier
                                .size(30.dp, 20.dp)
                                .clip(RoundedCornerShape(5.dp)),
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                        Image(
                            modifier = Modifier
                                .size(30.dp, 20.dp)
                                .clip(RoundedCornerShape(5.dp)),
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                        Image(
                            modifier = Modifier
                                .size(30.dp, 20.dp)
                                .clip(RoundedCornerShape(5.dp)),
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        modifier = Modifier.width(220.dp),
                        fontSize = 12.sp,
                        lineHeight = 15.sp,
                        text = if (data?.bio ?: null != null) data?.bio.toString() else "There's no bio.",
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                }
                if (data?.profile ?: null != null) {
                    AsyncImage(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape),
                        model = "${Constants.BASE_URL_DEV}/v1/profile/avatar/${data?.profile.toString()}",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }else {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile",
                        tint = Color.Black,
                        modifier = Modifier.size(130.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(25.dp)
                )
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!statusFollow) {
                    Button(
                        modifier = Modifier
                            .width(100.dp)
                            .border(
                                2.dp,
                                Color(0xFFFF8673),
                                RoundedCornerShape(8.dp)
                            )
                            .height(30.dp),
                        onClick = {
                            onFollow()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF8673),
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            fontSize = 10.sp,
                            text = "FOLLOW",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }else {
                    Button(
                        modifier = Modifier
                            .width(130.dp)
                            .border(
                                2.dp,
                                Color(0xFFFF8673),
                                RoundedCornerShape(8.dp)
                            )
                            .height(30.dp),
                        onClick = {
                            onUnfollow()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            fontSize = 10.sp,
                            text = "FOLLOWED",
                            color = Color(0xFFFF8673),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

}