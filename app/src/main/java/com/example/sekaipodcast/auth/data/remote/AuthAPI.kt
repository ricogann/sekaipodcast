package com.example.sekaipodcast.auth.data.remote

import com.example.sekaipodcast.auth.domain.model.Login
import com.example.sekaipodcast.auth.domain.model.Register
import com.example.sekaipodcast.auth.data.remote.dto.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("/v1/auth/register")
    suspend fun registerAccount(@Body requestBody: Register): Response

    @POST("/v1/auth/login")
    suspend fun loginAccount(@Body requestBody: Login): Response
}