package com.example.sekaipodcast.profile.data.remote.dto

data class Podcast(
    val comments: Any,
    val countrylogo: String,
    val countryname: String,
    val description: String,
    val id: String,
    val level: String,
    val likes: Any,
    val name: String,
    val podcastFilename: String,
    val reports: Any,
    val thumbnailFilename: String,
    val title: String,
    val totalcomment: Int,
    val totallikes: Int,
    val totallisten: Int
)