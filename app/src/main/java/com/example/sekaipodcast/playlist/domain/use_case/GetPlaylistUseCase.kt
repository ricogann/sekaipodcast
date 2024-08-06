package com.example.sekaipodcast.playlist.domain.use_case

import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.playlist.data.remote.dto.ResponsePlaylistUser
import com.example.sekaipodcast.playlist.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    operator fun invoke(userId: String): Flow<Resource<ResponsePlaylistUser>> = flow {
        try {
            emit(Resource.Loading<ResponsePlaylistUser>())
            val response = repository.getPlaylistUser(userId)
            emit(Resource.Success<ResponsePlaylistUser>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<ResponsePlaylistUser>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<ResponsePlaylistUser>("Couldn't reach server. Check your internet connection."))
            e.printStackTrace()
        }
    }
}