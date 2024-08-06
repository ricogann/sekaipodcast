package com.example.sekaipodcast.profile.domain.use_case

import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.profile.data.remote.dto.ResponseFollowInfo
import com.example.sekaipodcast.profile.domain.model.FollowInfo
import com.example.sekaipodcast.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FollowUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(body: FollowInfo): Flow<Resource<ResponseFollowInfo>> = flow {
        try {
            emit(Resource.Loading<ResponseFollowInfo>())
            val response = repository.follow(body)
            emit(Resource.Success<ResponseFollowInfo>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<ResponseFollowInfo>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<ResponseFollowInfo>("Couldn't reach server. Check your internet connection."))
        }
    }
}