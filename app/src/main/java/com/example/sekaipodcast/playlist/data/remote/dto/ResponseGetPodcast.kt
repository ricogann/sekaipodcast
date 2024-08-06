package com.example.sekaipodcast.playlist.data.remote.dto

data class ResponseGetPodcast(
    val `data`: List<Podcast>,
    val message: String,
    val status: Boolean
)