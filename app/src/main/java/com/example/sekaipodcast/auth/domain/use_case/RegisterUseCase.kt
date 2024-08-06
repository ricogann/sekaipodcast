package com.example.sekaipodcast.auth.domain.use_case

import android.util.Log
import com.example.sekaipodcast.auth.data.remote.dto.Response
import com.example.sekaipodcast.auth.domain.model.Register
import com.example.sekaipodcast.auth.domain.repository.AuthRepository
import com.example.sekaipodcast.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(requestBody: Register): Flow<Resource<Response>> = flow {
        try {
            emit(Resource.Loading<Response>())
            val response = repository.register(requestBody)
            emit(Resource.Success<Response>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<Response>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<Response>("Couldn't reach server. Check your internet connection."))
        }
    }
}