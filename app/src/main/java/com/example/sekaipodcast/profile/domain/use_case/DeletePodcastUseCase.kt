package com.example.sekaipodcast.profile.domain.use_case

import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.profile.data.remote.dto.ResponseDeletePodcast
import com.example.sekaipodcast.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeletePodcastUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(id: String): Flow<Resource<ResponseDeletePodcast>> = flow {
        try {
            emit(Resource.Loading<ResponseDeletePodcast>())
            val response = repository.deletePodcast(id)
            emit(Resource.Success<ResponseDeletePodcast>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<ResponseDeletePodcast>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<ResponseDeletePodcast>("Couldn't reach server. Check your internet connection."))
        }
    }
}