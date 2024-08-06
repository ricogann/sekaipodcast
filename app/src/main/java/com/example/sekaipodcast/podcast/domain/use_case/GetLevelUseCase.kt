package com.example.sekaipodcast.podcast.domain.use_case

import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.podcast.data.remote.dto.ResponseLevel
import com.example.sekaipodcast.podcast.domain.repository.PodcastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLevelUseCase @Inject constructor(
    private val repository: PodcastRepository
) {
    operator fun invoke(): Flow<Resource<ResponseLevel>> = flow {
        try {
            emit(Resource.Loading<ResponseLevel>())
            val response = repository.getLevel()
            emit(Resource.Success<ResponseLevel>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<ResponseLevel>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<ResponseLevel>("Couldn't reach server. Check your internet connection."))
            e.printStackTrace()
        }
    }
}