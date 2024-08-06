package com.example.sekaipodcast.onboarding.domain.repository

import kotlinx.coroutines.flow.Flow

interface LocalUser {

    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}