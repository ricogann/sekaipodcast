package com.example.sekaipodcast.profile.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.sekaipodcast.R
import com.example.sekaipodcast.Route
import com.example.sekaipodcast.common.Constants
import com.example.sekaipodcast.profile.data.remote.dto.DataUser
import com.example.sekaipodcast.profile.presentation.user.BottomSheetsItem
import com.example.sekaipodcast.ui.theme.SekaipodcastTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInfo(
    navController: NavController,
    modifier: Modifier = Modifier,
    user: DataUser,
    onLogout: () -> Unit,
    onOpenDialogFollower: () -> Unit,
    onOpenDialogFollowing: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    val items = listOf(
        BottomSheetsItem(
            title = "Logout",
            fontSize = 18,
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            action = {
                onLogout()
            }
        ),
    )

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
                Text(
                    fontSize = 20.sp,
                    text = user?.name ?: "",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    fontSize = 12.sp,
                    text = user?.email ?: "",
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        fontSize = 15.sp,
                        text = (user?.followers?.size ?: 0).toString() + " Followers",
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable { onOpenDialogFollower() },
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        fontSize = 15.sp,
                        text = (user?.following?.size ?: 0).toString() + " Following",
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable { onOpenDialogFollowing() }
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
                    text = if (user.bio != null) user.bio.toString() else "There's no bio.",
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )

            }
            Column {
                if (user?.profile ?: null != null) {
                    AsyncImage(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape),
                        model = "${Constants.BASE_URL_DEV}/v1/profile/avatar/${user?.profile.toString()}",
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
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp).clickable {
                            navController.navigate(Route.EditProfileScreen.route)
                        }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp).clickable {
                            isSheetOpen = true
                        }
                    )
                }
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
        UserBottomSheet(sheetState = sheetState, isSheetOpen = isSheetOpen, onDismiss = { isSheetOpen = false }, items = items)
    }
}