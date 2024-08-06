package com.example.sekaipodcast.podcast.domain.model

import com.google.gson.annotations.SerializedName

data class LikePodcast(
    @SerializedName("account") val account: String = "",
    @SerializedName("content") val content: String = "",
)
