package com.example.sekaipodcast.profile.domain.repository

import com.example.sekaipodcast.profile.data.remote.dto.ResponseCountry
import com.example.sekaipodcast.profile.data.remote.dto.ResponseDeletePodcast
import com.example.sekaipodcast.profile.data.remote.dto.ResponseFollowInfo
import com.example.sekaipodcast.profile.data.remote.dto.ResponseGetUser
import com.example.sekaipodcast.profile.domain.model.FollowInfo

interface ProfileRepository {
    suspend fun getCountry(): ResponseCountry

    suspend fun getDetailUser(id: String): ResponseGetUser

    suspend fun deletePodcast(id: String): ResponseDeletePodcast

    suspend fun logout()

    suspend fun followInfo(requestBody: FollowInfo): ResponseFollowInfo

    suspend fun follow(requestBody: FollowInfo): ResponseFollowInfo

    suspend fun unfollow(requestBody: FollowInfo): ResponseFollowInfo
}