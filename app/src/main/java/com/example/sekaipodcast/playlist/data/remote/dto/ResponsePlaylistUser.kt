package com.example.sekaipodcast.playlist.data.remote.dto

data class ResponsePlaylistUser(
    val `data`: List<Data>,
    val message: String,
    val status: Boolean
)