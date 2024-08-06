package com.example.sekaipodcast.podcast.data.remote

import com.example.sekaipodcast.podcast.data.remote.dto.ResponseAddPodcast
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseCountry
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseDetailPodcast
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseLevel
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseLikePodcast
import com.example.sekaipodcast.podcast.domain.model.LikePodcast
import com.example.sekaipodcast.podcast.domain.model.UploadPodcastRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface PodcastAPI {
    @GET("/v1/admin/get/country")
    suspend fun getCountry(): ResponseCountry

    @GET("/v1/admin/get/level")
    suspend fun getLevel(): ResponseLevel

    @GET("/v1/podcast/{id}")
    suspend fun getDetailPodcast(@Path("id") podcastId: String): ResponseDetailPodcast

    @POST("/v1/podcast/insert-likes")
    suspend fun likePodcast(@Body requestBody: LikePodcast): ResponseLikePodcast

    @POST("/v1/podcast/delete/likes")
    suspend fun unlikePodcast(@Body requestBody: LikePodcast): ResponseLikePodcast

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