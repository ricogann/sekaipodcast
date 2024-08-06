package com.example.sekaipodcast.auth.presentation.register

import com.example.sekaipodcast.auth.data.remote.dto.Response

data class RegisterState(
    val isLoading: Boolean = false,
    val response: Response = Response(),
    val error: String = ""
)
