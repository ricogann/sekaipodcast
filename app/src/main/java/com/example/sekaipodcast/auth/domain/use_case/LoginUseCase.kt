package com.example.sekaipodcast.auth.domain.use_case

import com.example.sekaipodcast.auth.data.remote.dto.Response
import com.example.sekaipodcast.auth.domain.model.Login
import com.example.sekaipodcast.auth.domain.repository.AuthRepository
import com.example.sekaipodcast.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(requestBody: Login): Flow<Resource<Response>> = flow {
        try {
            emit(Resource.Loading<Response>())
            val response = repository.login(requestBody)
            emit(Resource.Success<Response>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<Response>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<Response>("Couldn't reach server. Check your internet connection."))
        }
    }
}