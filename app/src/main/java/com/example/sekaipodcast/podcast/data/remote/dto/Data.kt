package com.example.sekaipodcast.podcast.data.remote.dto

data class Data(
    val comments: List<Comment>,
    val countrylogo: String,
    val countryname: String,
    val description: String,
    val id: String,
    val level: String,
    val likes: List<Like>,
    val name: String,
    val podcastFilename: String,
    val reports: Any,
    val thumbnailFilename: String,
    val title: String,
    val totalcomment: Int,
    val totallikes: Int,
    val totallisten: Int
)