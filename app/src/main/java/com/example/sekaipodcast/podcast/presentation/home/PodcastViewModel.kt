package com.example.sekaipodcast.podcast.presentation.home

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
import com.example.sekaipodcast.player.service.Music
import com.example.sekaipodcast.player.service.PlayerHandlers
import com.example.sekaipodcast.podcast.data.remote.dto.Data
import com.example.sekaipodcast.podcast.data.remote.dto.Like
import com.example.sekaipodcast.podcast.domain.model.LikePodcast
import com.example.sekaipodcast.podcast.domain.use_case.GetDetailPodcastUseCase
import com.example.sekaipodcast.podcast.domain.use_case.LikePodcastUseCase
import com.example.sekaipodcast.podcast.domain.use_case.UnlikePodcastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class PodcastViewModel @Inject constructor(
    private val playerHandlers: PlayerHandlers,
    private val getDetailPodcastUseCase: GetDetailPodcastUseCase,
    private val readLoginEntryUseCase: ReadLoginEntryUseCase,
    private val likePodcastUseCase: LikePodcastUseCase,
    private val unlikePodcastUseCase: UnlikePodcastUseCase
) : ViewModel() {
    var isPlaying by mutableStateOf(false)
    var currentPosition by mutableStateOf(0L)
    var sliderPosition by mutableStateOf(0L)
    var totalDuration by mutableStateOf(0L)
    var playingSongIndex by mutableStateOf(0)

    var statusLike by mutableStateOf(false)

    var id by mutableStateOf<String>("")
        private set

    var like by mutableStateOf<Like?>(null)
        private set

    var podcast by mutableStateOf<Data?>(null)

    init {
        readLoginEntryUseCase().onEach { token ->
            if (token.isNotEmpty()) {
                val res = parseJWT(token)
                id = res.getString("id")
            }
        }.launchIn(viewModelScope)
        updatePlayerState()
        updatePlayerDuration()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseJWT(token: String): JSONObject {
        val decodedJWT: DecodedJWT = JWT.decode(token)
        val payload = decodedJWT.payload
        val decodedBytes = java.util.Base64.getDecoder().decode(payload)
        val decodedString = String(decodedBytes)
        return JSONObject(decodedString)
    }

    fun getDetailPodcast(podcastId: String) {
        viewModelScope.launch {
            getDetailPodcastUseCase(podcastId).collect{ resource ->
                when (resource) {
                    is Resource.Loading -> Log.d("testing", "Loading")
                    is Resource.Success -> {
                        podcast = resource.data?.data
                        val likes = resource.data?.data?.likes ?: emptyList()
                        like = likes.find { it.account == id }
                        if (like != null) {
                            statusLike = true
                        }
                    }
                    is Resource.Error -> Log.d("testing", "Error: ${resource.message}")
                }
            }
        }
    }

    fun likePodcast(podcastId: String) {
        viewModelScope.launch {
            likePodcastUseCase(LikePodcast(account = id, content = podcastId)).collect{ resource ->
                when (resource) {
                    is Resource.Loading -> Log.d("testing", "Loading")
                    is Resource.Success -> {
                        if (resource.data?.status == true) {
                            statusLike = true
                        }
                    }
                    is Resource.Error -> Log.d("testing", "Error: ${resource.message}")
                }
            }
        }
    }

    fun unlikePodcast(podcastId: String) {
        viewModelScope.launch {
            unlikePodcastUseCase(LikePodcast(account = id, content = podcastId)).collect{ resource ->
                when (resource) {
                    is Resource.Loading -> Log.d("testing", "Loading")
                    is Resource.Success -> {
                        if (resource.data?.status == true) {
                            statusLike = false
                        }
                    }
                    is Resource.Error -> Log.d("testing", "Error: ${resource.message}")
                }
            }
        }
    }

    fun initPlayer(playList: List<Music>) {
        playerHandlers.initPlayer(playList)
        updatePlayerState()
        updatePlayerDuration()
    }

    private fun updatePlayerState() {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                isPlaying = playerHandlers.isPlaying()
                currentPosition = playerHandlers.getCurrentPosition()
                sliderPosition = currentPosition
            }
        }
    }

    private fun updatePlayerDuration() {
        if (playerHandlers.getDuration() > 0) {
            totalDuration = playerHandlers.getDuration()
        }
    }

    fun playPause() {
        playerHandlers.playPause()
        isPlaying = playerHandlers.isPlaying()
        updatePlayerState()
        updatePlayerDuration()
    }

    fun seekToNext() = playerHandlers.seekToNext()

    fun seekToPrevious() = playerHandlers.seekToPrevious()

    fun getCurrentMediaIndex() = playerHandlers.getCurrentMediaIndex()

    fun seekTo(position: Long) {
        playerHandlers.seekTo(position)
        sliderPosition = position
        currentPosition = position
    }

    fun onPageChange(pageIndex: Int) {
        playingSongIndex = pageIndex
        playerHandlers.seekTo(pageIndex.toLong())
    }

    override fun onCleared() {
        super.onCleared()
        playerHandlers.exoPlayer.release()
    }
}

