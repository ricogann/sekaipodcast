package com.example.sekaipodcast.playlist.data.remote.dto

data class DataPlaylistDetail(
    val description: String,
    val name: String,
    val nameaccount: String,
    val podcasts: List<Podcast>
)