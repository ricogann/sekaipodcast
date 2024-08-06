package com.example.sekaipodcast.playlist.presentation.detailplaylist

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.playlist.data.remote.dto.ResponsePlaylistDetail
import com.example.sekaipodcast.playlist.domain.use_case.DeletePlaylistUseCase
import com.example.sekaipodcast.playlist.domain.use_case.GetPlaylistDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPlaylistViewModel @Inject constructor(
    private val getPlaylistDetailUseCase: GetPlaylistDetailUseCase,
    private val deletePlaylistUseCase: DeletePlaylistUseCase
): ViewModel() {
    var response by mutableStateOf<ResponsePlaylistDetail?>(null)

    var statusDelete by mutableStateOf(false)

    fun getPlaylistDetail(playlistId: String) {
        viewModelScope.launch {
            getPlaylistDetailUseCase(playlistId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> Log.d("testing", "Loading")
                    is Resource.Success -> {
                        response = resource.data
                    }
                    is Resource.Error -> Log.d("testing", "Error: ${resource.message}")
                }
            }
        }
    }

    fun deletePlaylist(playlistId: String) {
        viewModelScope.launch {
            deletePlaylistUseCase(playlistId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> Log.d("testing", "Loading")
                    is Resource.Success -> {
                        statusDelete = resource.data?.status ?: false
                    }
                    is Resource.Error -> Log.d("testing", "Error: ${resource.message}")
                }
            }
        }
    }
}