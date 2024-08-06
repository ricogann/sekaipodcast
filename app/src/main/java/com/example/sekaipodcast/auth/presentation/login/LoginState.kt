package com.example.sekaipodcast.auth.presentation.login

import com.example.sekaipodcast.auth.data.remote.dto.Response

data class LoginState(
    val isLoading: Boolean = false,
    val response: Response = Response(),
    val error: String = ""
)
