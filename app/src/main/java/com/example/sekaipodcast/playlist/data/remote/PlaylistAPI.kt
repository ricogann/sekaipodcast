package com.example.sekaipodcast.playlist.data.remote

import com.example.sekaipodcast.playlist.data.remote.dto.ResponseCreatePlaylist
import com.example.sekaipodcast.playlist.data.remote.dto.ResponseDeletePlaylist
import com.example.sekaipodcast.playlist.data.remote.dto.ResponsePlaylistDetail
import com.example.sekaipodcast.playlist.data.remote.dto.ResponsePlaylistUser
import com.example.sekaipodcast.playlist.domain.model.CreatePlaylistRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PlaylistAPI {
    @POST("/v1/playlist/insert")
    suspend fun createPlaylist(@Body requestBody: CreatePlaylistRequest): ResponseCreatePlaylist

    @GET("/v1/playlist/user/{id}")
    suspend fun getPlaylistUser(@Path("id") userId: String): ResponsePlaylistUser
    
    @GET("/v1/playlist/{id}")
    suspend fun getPlaylistUserDetail(@Path("id") playlistId: String): ResponsePlaylistDetail

    @DELETE("/v1/playlist/{id}")
    suspend fun deletePlaylist(@Path("id") playlistId: String): ResponseDeletePlaylist
}