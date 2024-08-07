package com.example.sekaipodcast.podcast.domain.repository

import com.example.sekaipodcast.podcast.data.remote.dto.ResponseAddPodcast
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseCountry
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseDetailPodcast
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseLevel
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseLikePodcast
import com.example.sekaipodcast.podcast.domain.model.LikePodcast
import com.example.sekaipodcast.podcast.domain.model.UploadPodcastRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface PodcastRepository {
    suspend fun getCountry(): ResponseCountry

    suspend fun getLevel(): ResponseLevel

    suspend fun getDetailPodcast(podcastId: String): ResponseDetailPodcast

    suspend fun likePodcast(requestBody: LikePodcast): ResponseLikePodcast

    suspend fun unlikePodcast(requestBody: LikePodcast): ResponseLikePodcast

    suspend fun uploadPodcast(
        title: RequestBody,
        description: RequestBody,
        country: RequestBody,
        level: RequestBody,
        account: RequestBody,
        thumbnail: MultipartBody.Part?,
        podcast: MultipartBody.Part?
    ): ResponseAddPodcast
}