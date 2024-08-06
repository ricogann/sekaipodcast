package com.example.sekaipodcast.podcast.domain.use_case

import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseLikePodcast
import com.example.sekaipodcast.podcast.domain.model.LikePodcast
import com.example.sekaipodcast.podcast.domain.repository.PodcastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UnlikePodcastUseCase @Inject constructor(
    private val repository: PodcastRepository
) {
    suspend operator fun invoke(requestBody: LikePodcast): Flow<Resource<ResponseLikePodcast>> = flow {
        try {
            emit(Resource.Loading<ResponseLikePodcast>())
            val response = repository.unlikePodcast(requestBody)
            emit(Resource.Success<ResponseLikePodcast>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<ResponseLikePodcast>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<ResponseLikePodcast>("Couldn't reach server. Check your internet connection."))
            e.printStackTrace()
        }
    }
}