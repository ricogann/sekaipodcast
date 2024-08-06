package com.example.sekaipodcast.podcast.data.remote.dto

data class ResponseCountry(
    val data: List<DataCountry>,
    val message: String,
    val status: Boolean
)