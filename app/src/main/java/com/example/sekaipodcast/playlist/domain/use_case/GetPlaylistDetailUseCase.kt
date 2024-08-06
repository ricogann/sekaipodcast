package com.example.sekaipodcast.playlist.domain.use_case

import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.playlist.data.remote.dto.ResponsePlaylistDetail
import com.example.sekaipodcast.playlist.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPlaylistDetailUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    operator fun invoke(playlistId: String): Flow<Resource<ResponsePlaylistDetail>> = flow {
        try {
            emit(Resource.Loading<ResponsePlaylistDetail>())
            val response = repository.getPlaylistDetailUser(playlistId)
            emit(Resource.Success<ResponsePlaylistDetail>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<ResponsePlaylistDetail>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<ResponsePlaylistDetail>("Couldn't reach server. Check your internet connection."))
            e.printStackTrace()
        }
    }
}