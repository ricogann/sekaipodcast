package com.example.sekaipodcast.profile.data.remote.dto

data class DataUser(
    val bio: Any,
    val country: Any,
    val countrylogo: Any,
    val email: String,
    val followers: List<DataMutual>,
    val following: List<DataMutual>,
    val id: String,
    val instagram: Any,
    val name: String,
    val podcast: List<Podcast>,
    val profile: Any,
    val reports: Any,
    val totalcontent: Int,
    val totallisten: Int,
    val website: Any,
    val youtube: Any
)