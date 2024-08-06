package com.example.sekaipodcast.profile.data.remote.dto

data class ResponseGetUser(
    val `data`: DataUser,
    val message: String,
    val status: Boolean
)