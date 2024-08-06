package com.example.sekaipodcast.playlist.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.playlist.data.remote.dto.Data
import com.example.sekaipodcast.playlist.domain.model.CreatePlaylistRequest
import com.example.sekaipodcast.playlist.domain.use_case.CreatePlaylistUseCase
import com.example.sekaipodcast.playlist.domain.use_case.GetPlaylistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePlaylistViewModel @Inject constructor(
    private val createPlaylistUseCase: CreatePlaylistUseCase,
    private val getPlaylistUserUseCase: GetPlaylistUseCase
): ViewModel() {
    var statusCreate by mutableStateOf(false)

    var playlists by mutableStateOf<List<Data>?>(null)
            private set

    init {
        getPlaylistUser("91ceaca6-c228-4b69-99bf-2eeb7d33f224")
    }

    fun createPlaylist(requestBody: CreatePlaylistRequest) {
        viewModelScope.launch {
            createPlaylistUseCase(requestBody).collect { resource ->
                when (resource) {
                    is Resource.Loading -> Log.d("testing", "Loading")
                    is Resource.Success -> {
                        statusCreate = resource.data?.status == true
                    }
                    is Resource.Error -> Log.d("testing", "Error: ${resource.message}")
                }
            }
        }
    }

    private fun getPlaylistUser(userId: String) {
        viewModelScope.launch {
            getPlaylistUserUseCase(userId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> Log.d("testing", "Loading")
                    is Resource.Success -> {
                        playlists = resource.data?.data
                    }
                    is Resource.Error -> Log.d("testing", "Error: ${resource.message}")
                }
            }
        }
    }
}