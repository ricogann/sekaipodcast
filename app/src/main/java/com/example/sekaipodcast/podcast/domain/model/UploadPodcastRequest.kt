package com.example.sekaipodcast.podcast.domain.model

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class UploadPodcastRequest(
    val title: RequestBody,
    val description: RequestBody,
    val country: RequestBody,
    val level: RequestBody,
    val account: RequestBody,
    val image: MultipartBody.Part?,
    val podcast: MultipartBody.Part?
)
