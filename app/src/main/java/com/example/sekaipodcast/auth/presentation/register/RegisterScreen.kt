package com.example.sekaipodcast.auth.presentation.register

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sekaipodcast.Route
import com.example.sekaipodcast.auth.domain.model.Register
import com.example.sekaipodcast.auth.presentation.login.components.GreetingsText
import com.example.sekaipodcast.auth.presentation.login.components.InputText
import com.example.sekaipodcast.auth.presentation.register.components.RegisterButton
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val state by viewModel.state
    var nameState by remember { mutableStateOf(TextFieldValue()) }
    var emailState by remember { mutableStateOf(TextFieldValue()) }
    var passwordState by remember { mutableStateOf(TextFieldValue()) }

    LaunchedEffect(state.response.status) {
        if (state.response.status) {
            scope.launch {
                snackbarHostState.showSnackbar("Registration Successful!")
                navController.navigate(Route.LoginScreen.route)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier,
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    GreetingsText(
                        title = "SEKAI PODCAST",
                        subtitle = "#1 LEARNING LANGUAGE PLATFORM",
                        fontSizeTitle = 35.sp,
                        fontSizeSubtitle = 15.sp,
                        alignment = Alignment.CenterHorizontally,
                        color = Color(0xFFFF8673)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    GreetingsText(
                        title = "REGISTER ACCOUNT",
                        subtitle = "Let's enhance our language skill!",
                        fontSizeTitle = 20.sp,
                        fontSizeSubtitle = 15.sp,
                        alignment = Alignment.CenterHorizontally,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    InputText(value = nameState, onValueChange = { nameState = it }, label = "Name")
                    Spacer(modifier = Modifier.height(6.dp))
                    InputText(value = emailState, onValueChange = { emailState = it }, label = "Email")
                    Spacer(modifier = Modifier.height(6.dp))
                    InputText(value = passwordState, onValueChange = { passwordState = it }, label = "Password")
                    Spacer(modifier = Modifier.height(16.dp))
                    RegisterButton(onClick = {
                        val registerRequest = Register(
                            name = nameState.text,
                            email = emailState.text,
                            password = passwordState.text
                        )

                        viewModel.register(registerRequest)
                    }, buttonText = "REGISTER")
                    Spacer(modifier = Modifier.height(40.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            fontSize = 14.sp,
                            text = "Already have an account?",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Button(
                            onClick = {
                                navController.navigate(Route.LoginScreen.route)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                            ),
                            shape = CircleShape,
                            contentPadding = PaddingValues(0.dp),
                        ) {
                            Text(
                                fontSize = 14.sp,
                                text = "Login Now",
                                color = Color(0xFFFF8673),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    )
}