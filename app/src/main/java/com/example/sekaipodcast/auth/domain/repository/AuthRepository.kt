package com.example.sekaipodcast.auth.domain.repository

import com.example.sekaipodcast.auth.domain.model.Login
import com.example.sekaipodcast.auth.domain.model.Register
import com.example.sekaipodcast.auth.data.remote.dto.Response
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(requestBody: Register): Response

    suspend fun login(requestBody: Login): Response

    suspend fun saveLoginEntry(token: String)

    fun readLoginEntry(): Flow<String>
}