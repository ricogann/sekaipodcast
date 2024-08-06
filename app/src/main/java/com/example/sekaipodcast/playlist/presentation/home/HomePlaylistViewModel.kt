package com.example.sekaipodcast.playlist.presentation.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT
import com.example.sekaipodcast.auth.domain.use_case.ReadLoginEntryUseCase
import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.playlist.data.remote.dto.Data
import com.example.sekaipodcast.playlist.domain.model.CreatePlaylistRequest
import com.example.sekaipodcast.playlist.domain.use_case.CreatePlaylistUseCase
import com.example.sekaipodcast.playlist.domain.use_case.GetPlaylistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomePlaylistViewModel @Inject constructor(
    private val createPlaylistUseCase: CreatePlaylistUseCase,
    private val readLoginEntryUseCase: ReadLoginEntryUseCase,
    private val getPlaylistUserUseCase: GetPlaylistUseCase
): ViewModel() {
    var statusCreate by mutableStateOf(false)

    var playlists by mutableStateOf<List<Data>?>(null)
            private set

    var id by mutableStateOf<String>("")
        private set

    init {
        readLoginEntryUseCase().onEach { token ->
            if (token.isNotEmpty()) {
                val res = parseJWT(token)
                id = res.getString("id")
            }
            getPlaylistUser(id)
        }.launchIn(viewModelScope)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseJWT(token: String): JSONObject {
        val decodedJWT: DecodedJWT = JWT.decode(token)
        val payload = decodedJWT.payload
        val decodedBytes = java.util.Base64.getDecoder().decode(payload)
        val decodedString = String(decodedBytes)
        return JSONObject(decodedString)
    }

    fun createPlaylist(requestBody: CreatePlaylistRequest) {
        viewModelScope.launch {
            createPlaylistUseCase(requestBody).collect { resource ->
                when (resource) {
                    is Resource.Loading -> Log.d("testing", "Loading")
                    is Resource.Success -> {
                        statusCreate = resource.data?.status == true
                        getPlaylistUser(id)
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