package com.example.sekaipodcast.profile.domain.use_case

import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.profile.data.remote.dto.ResponseCountry
import com.example.sekaipodcast.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCountryUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(): Flow<Resource<ResponseCountry>> = flow {
        try {
            emit(Resource.Loading<ResponseCountry>())
            val response = repository.getCountry()
            emit(Resource.Success<ResponseCountry>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<ResponseCountry>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<ResponseCountry>("Couldn't reach server. Check your internet connection."))
        }
    }
}