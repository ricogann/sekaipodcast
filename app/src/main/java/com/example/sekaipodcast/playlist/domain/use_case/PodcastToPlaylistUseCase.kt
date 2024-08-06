package com.example.sekaipodcast.playlist.domain.use_case

import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.playlist.data.remote.dto.ResponsePodcastToPlaylist
import com.example.sekaipodcast.playlist.domain.model.PodcastToPlaylist
import com.example.sekaipodcast.playlist.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PodcastToPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    operator fun invoke(requestBody: PodcastToPlaylist): Flow<Resource<ResponsePodcastToPlaylist>> = flow {
        try {
            emit(Resource.Loading<ResponsePodcastToPlaylist>())
            val response = repository.podcastToPlaylist(requestBody)
            emit(Resource.Success<ResponsePodcastToPlaylist>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<ResponsePodcastToPlaylist>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<ResponsePodcastToPlaylist>("Couldn't reach server. Check your internet connection."))
            e.printStackTrace()
        }
    }
}