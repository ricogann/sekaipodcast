package com.example.sekaipodcast.profile.presentation.editprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sekaipodcast.SetStatusBarColor
import com.example.sekaipodcast.profile.presentation.editprofile.components.DropdownCountry
import com.example.sekaipodcast.profile.presentation.editprofile.components.InputText

@Composable
fun EditProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: EditProfileViewModel = hiltViewModel()
) {
    SetStatusBarColor(color = Color.White)
    var nameState by remember { mutableStateOf(TextFieldValue()) }
    var emailState by remember { mutableStateOf(TextFieldValue()) }
    var passwordState by remember { mutableStateOf(TextFieldValue()) }
    var bioState by remember { mutableStateOf(TextFieldValue()) }
    var websiteState by remember { mutableStateOf(TextFieldValue()) }
    var youtubeState by remember { mutableStateOf(TextFieldValue()) }
    var instagramState by remember { mutableStateOf(TextFieldValue()) }
    var countryState by remember { mutableStateOf<String?>(null) }
    var learnLanguageState1 by remember { mutableStateOf<String?>(null) }
    var learnLanguageState2 by remember { mutableStateOf<String?>(null) }

    var countryError by remember { mutableStateOf<String?>(null) }

    var countries = viewModel.countries ?: emptyList()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.popBackStack()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFFFF8673),
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    fontSize = 20.sp,
                    text = "Edit Profile",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .size(240.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color(0xFFEFEFEF)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Foto Profile",
                    color = Color.Gray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            InputText(value = nameState, onValueChange = { nameState = it }, label = "Name")
            InputText(value = emailState, onValueChange = { emailState = it }, label = "Email")
            DropdownCountry(
                label = "Country",
                items = countries,
                selectedId = countryState,
                onSelect = { id -> countryState = id },
                errorMessage = countryError
            )
            InputText(value = passwordState, onValueChange = { passwordState = it }, label = "Password")
            InputText(value = bioState, onValueChange = { bioState = it }, label = "Bio")
            InputText(value = websiteState, onValueChange = { websiteState = it }, label = "Website")
            InputText(value = youtubeState, onValueChange = { youtubeState = it }, label = "Youtube")
            InputText(value = instagramState, onValueChange = { instagramState = it }, label = "Instagram")
            DropdownCountry(
                label = "First Language",
                items = countries,
                selectedId = learnLanguageState1,
                onSelect = { id -> learnLanguageState1 = id },
                errorMessage = countryError
            )
            DropdownCountry(
                label = "Second Language",
                items = countries,
                selectedId = learnLanguageState2,
                onSelect = { id -> learnLanguageState2 = id },
                errorMessage = countryError
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { },
                modifier = modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF8376)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "SAVE EDIT",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}