package com.example.sekaipodcast.profile.domain.use_case

import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.profile.data.remote.dto.ResponseGetUser
import com.example.sekaipodcast.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDetailUserUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(id: String): Flow<Resource<ResponseGetUser>> = flow {
        try {
            emit(Resource.Loading<ResponseGetUser>())
            val response = repository.getDetailUser(id)
            emit(Resource.Success<ResponseGetUser>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<ResponseGetUser>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<ResponseGetUser>("Couldn't reach server. Check your internet connection."))
        }
    }
}