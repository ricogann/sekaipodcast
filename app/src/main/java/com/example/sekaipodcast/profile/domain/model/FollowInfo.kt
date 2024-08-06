package com.example.sekaipodcast.profile.domain.model

import com.google.gson.annotations.SerializedName

data class FollowInfo(
    @SerializedName("firstUser") val first: String = "",
    @SerializedName("secondUser") val second: String = "",
)
