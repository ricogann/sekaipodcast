package com.example.sekaipodcast.playlist.domain.model

import com.google.gson.annotations.SerializedName

data class CreatePlaylistRequest(
    @SerializedName("account") val account: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String = ""
)
