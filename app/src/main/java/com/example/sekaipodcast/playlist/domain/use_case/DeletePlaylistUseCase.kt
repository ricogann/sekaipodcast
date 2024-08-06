package com.example.sekaipodcast.playlist.domain.use_case

import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.playlist.data.remote.dto.ResponseDeletePlaylist
import com.example.sekaipodcast.playlist.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeletePlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    operator fun invoke(playlistId: String): Flow<Resource<ResponseDeletePlaylist>> = flow {
        try {
            emit(Resource.Loading<ResponseDeletePlaylist>())
            val response = repository.deletePlaylist(playlistId)
            emit(Resource.Success<ResponseDeletePlaylist>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<ResponseDeletePlaylist>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<ResponseDeletePlaylist>("Couldn't reach server. Check your internet connection."))
            e.printStackTrace()
        }
    }
}