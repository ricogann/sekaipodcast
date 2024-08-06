package com.example.sekaipodcast.playlist.data.remote.dto

data class Podcast(
    val countrylogo: String,
    val duration: Int,
    val id: String,
    val name: String,
    val podcasts: String,
    val thumbnail: String,
    val title: String,
    val totallikes: Int,
    val totallisten: Int
)