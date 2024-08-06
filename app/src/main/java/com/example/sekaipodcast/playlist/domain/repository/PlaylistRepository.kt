package com.example.sekaipodcast.playlist.domain.repository

import com.example.sekaipodcast.playlist.data.remote.dto.ResponseCreatePlaylist
import com.example.sekaipodcast.playlist.data.remote.dto.ResponseDeletePlaylist
import com.example.sekaipodcast.playlist.data.remote.dto.ResponseGetPodcast
import com.example.sekaipodcast.playlist.data.remote.dto.ResponsePlaylistDetail
import com.example.sekaipodcast.playlist.data.remote.dto.ResponsePlaylistUser
import com.example.sekaipodcast.playlist.data.remote.dto.ResponsePodcastToPlaylist
import com.example.sekaipodcast.playlist.domain.model.CreatePlaylistRequest
import com.example.sekaipodcast.playlist.domain.model.PodcastToPlaylist

interface PlaylistRepository {
    suspend fun createPlaylist(requestBody: CreatePlaylistRequest): ResponseCreatePlaylist

    suspend fun getPlaylistUser(userId: String): ResponsePlaylistUser

    suspend fun getPodcast(): ResponseGetPodcast

    suspend fun podcastToPlaylist(requestBody: PodcastToPlaylist): ResponsePodcastToPlaylist

    suspend fun getPlaylistDetailUser(playlistId: String): ResponsePlaylistDetail

    suspend fun deletePlaylist(playlistId: String): ResponseDeletePlaylist
}