package com.example.sekaipodcast.profile.data.remote

import com.example.sekaipodcast.profile.data.remote.dto.ResponseCountry
import com.example.sekaipodcast.profile.data.remote.dto.ResponseDeletePodcast
import com.example.sekaipodcast.profile.data.remote.dto.ResponseFollowInfo
import com.example.sekaipodcast.profile.data.remote.dto.ResponseGetUser
import com.example.sekaipodcast.profile.domain.model.FollowInfo
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileAPI {
    @GET("/v1/admin/get/country")
    suspend fun getCountry(): ResponseCountry

    @GET("/v1/user/{id}")
    suspend fun getDetailUser(@Path("id") userId: String): ResponseGetUser

    @DELETE("/v1/podcast/{id}")
    suspend fun deletePodcast(@Path("id") id: String): ResponseDeletePodcast

    @POST("/v1/user/follow-info")
    suspend fun followInfo(@Body requestBody: FollowInfo): ResponseFollowInfo

    @POST("/v1/user/follow")
    suspend fun follow(@Body requestBody: FollowInfo): ResponseFollowInfo

    @POST("/v1/user/unfollow")
    suspend fun unfollow(@Body requestBody: FollowInfo): ResponseFollowInfo
}