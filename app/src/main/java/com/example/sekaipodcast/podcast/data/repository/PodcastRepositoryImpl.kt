package com.example.sekaipodcast.podcast.data.repository

import android.util.Log
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseCountry
import com.example.sekaipodcast.podcast.data.remote.PodcastAPI
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseAddPodcast
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseLevel
import com.example.sekaipodcast.podcast.domain.model.UploadPodcastRequest
import com.example.sekaipodcast.podcast.domain.repository.PodcastRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class PodcastRepositoryImpl @Inject constructor(
    private val api: PodcastAPI
): PodcastRepository {

    override suspend fun getCountry(): ResponseCountry {
        return api.getCountry()
    }

    override suspend fun getLevel(): ResponseLevel {
        return api.getLevel()
    }

    override suspend fun uploadPodcast(
        title: RequestBody,
        description: RequestBody,
        country: RequestBody,
        level: RequestBody,
        account: RequestBody,
        thumbnail: MultipartBody.Part?,
        podcast: MultipartBody.Part?
    ): ResponseAddPodcast {
        return api.uploadPodcast(title, description, country, level, account, thumbnail, podcast)
    }

}