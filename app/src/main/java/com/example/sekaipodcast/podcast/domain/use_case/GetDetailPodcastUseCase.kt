package com.example.sekaipodcast.podcast.domain.use_case

import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseDetailPodcast
import com.example.sekaipodcast.podcast.domain.repository.PodcastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDetailPodcastUseCase @Inject constructor(
    private val repository: PodcastRepository
) {
    operator fun invoke(podcastId: String): Flow<Resource<ResponseDetailPodcast>> = flow {
        try {
            emit(Resource.Loading<ResponseDetailPodcast>())
            val response = repository.getDetailPodcast(podcastId)
            emit(Resource.Success<ResponseDetailPodcast>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<ResponseDetailPodcast>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<ResponseDetailPodcast>("Couldn't reach server. Check your internet connection."))
            e.printStackTrace()
        }
    }
}