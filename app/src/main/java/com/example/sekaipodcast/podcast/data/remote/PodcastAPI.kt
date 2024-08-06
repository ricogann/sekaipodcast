package com.example.sekaipodcast.podcast.data.remote

import com.example.sekaipodcast.podcast.data.remote.dto.ResponseAddPodcast
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseCountry
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseLevel
import com.example.sekaipodcast.podcast.domain.model.UploadPodcastRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PodcastAPI {
    @GET("/v1/admin/get/country")
    suspend fun getCountry(): ResponseCountry

    @GET("/v1/admin/get/level")
    suspend fun getLevel(): ResponseLevel

    @Multipart
    @POST("/v1/podcast/insert")
    suspend fun uploadPodcast(
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part("country") country: RequestBody,
        @Part("level") level: RequestBody,
        @Part("account") account: RequestBody,
        @Part thumbnail: MultipartBody.Part?,
        @Part podcast: MultipartBody.Part?
    ): ResponseAddPodcast
}