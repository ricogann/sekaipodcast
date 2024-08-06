package com.example.sekaipodcast.playlist.domain.use_case

import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.playlist.data.remote.dto.ResponseGetPodcast
import com.example.sekaipodcast.playlist.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPodcastUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    operator fun invoke(): Flow<Resource<ResponseGetPodcast>> = flow {
        try {
            emit(Resource.Loading<ResponseGetPodcast>())
            val response = repository.getPodcast()
            emit(Resource.Success<ResponseGetPodcast>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<ResponseGetPodcast>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<ResponseGetPodcast>("Couldn't reach server. Check your internet connection."))
            e.printStackTrace()
        }
    }
}