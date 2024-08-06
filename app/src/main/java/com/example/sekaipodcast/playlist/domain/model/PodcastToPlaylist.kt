package com.example.sekaipodcast.playlist.domain.model

import com.google.gson.annotations.SerializedName

data class PodcastToPlaylist(
    @SerializedName("podcast") val podcast: String = "",
    @SerializedName("playlist") val playlist: String = "",
)
