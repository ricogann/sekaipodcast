package com.example.sekaipodcast.playlist.data.remote.dto

data class ResponsePlaylistDetail(
    val `data`: DataPlaylistDetail,
    val message: String,
    val status: Boolean
)