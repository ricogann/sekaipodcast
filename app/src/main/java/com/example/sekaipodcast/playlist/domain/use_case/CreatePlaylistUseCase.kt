package com.example.sekaipodcast.playlist.domain.use_case

import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.playlist.data.remote.dto.ResponseCreatePlaylist
import com.example.sekaipodcast.playlist.domain.model.CreatePlaylistRequest
import com.example.sekaipodcast.playlist.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreatePlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    operator fun invoke(requestBody: CreatePlaylistRequest): Flow<Resource<ResponseCreatePlaylist>> = flow {
        try {
            emit(Resource.Loading<ResponseCreatePlaylist>())
            val response = repository.createPlaylist(requestBody)
            emit(Resource.Success<ResponseCreatePlaylist>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<ResponseCreatePlaylist>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<ResponseCreatePlaylist>("Couldn't reach server. Check your internet connection."))
            e.printStackTrace()
        }
    }
}