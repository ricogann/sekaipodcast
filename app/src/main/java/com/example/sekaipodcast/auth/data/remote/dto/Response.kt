package com.example.sekaipodcast.auth.data.remote.dto

data class Response(
    val message: String = "",
    val token: String = "",
    val status: Boolean = false,
)