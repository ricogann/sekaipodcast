package com.example.sekaipodcast.playlist.data.repository

import com.example.sekaipodcast.playlist.data.remote.PlaylistAPI
import com.example.sekaipodcast.playlist.data.remote.dto.ResponseCreatePlaylist
import com.example.sekaipodcast.playlist.data.remote.dto.ResponseDeletePlaylist
import com.example.sekaipodcast.playlist.data.remote.dto.ResponsePlaylistDetail
import com.example.sekaipodcast.playlist.data.remote.dto.ResponsePlaylistUser
import com.example.sekaipodcast.playlist.domain.model.CreatePlaylistRequest
import com.example.sekaipodcast.playlist.domain.repository.PlaylistRepository
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val api: PlaylistAPI
): PlaylistRepository {

    override suspend fun createPlaylist(requestBody: CreatePlaylistRequest): ResponseCreatePlaylist {
        return api.createPlaylist(requestBody)
    }

    override suspend fun getPlaylistUser(userId: String): ResponsePlaylistUser {
        return api.getPlaylistUser(userId)
    }

    override suspend fun getPlaylistDetailUser(playlistId: String): ResponsePlaylistDetail {
        return api.getPlaylistUserDetail(playlistId)
    }

    override suspend fun deletePlaylist(playlistId: String): ResponseDeletePlaylist {
        return api.deletePlaylist(playlistId)
    }

}