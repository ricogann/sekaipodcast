package com.example.sekaipodcast.podcast.domain.use_case

import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseAddPodcast
import com.example.sekaipodcast.podcast.domain.model.UploadPodcastRequest
import com.example.sekaipodcast.podcast.domain.repository.PodcastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UploadPodcastUseCase @Inject constructor(
    private val repository: PodcastRepository
) {
    operator fun invoke(
        title: RequestBody,
        description: RequestBody,
        country: RequestBody,
        level: RequestBody,
        account: RequestBody,
        thumbnail: MultipartBody.Part?,
        podcast: MultipartBody.Part?
    ): Flow<Resource<ResponseAddPodcast>> = flow {
        try {
            emit(Resource.Loading<ResponseAddPodcast>())
            val response = repository.uploadPodcast(title, description, country, level, account, thumbnail, podcast)
            emit(Resource.Success<ResponseAddPodcast>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<ResponseAddPodcast>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<ResponseAddPodcast>("Couldn't reach server. Check your internet connection."))
            e.printStackTrace()
        }
    }
}