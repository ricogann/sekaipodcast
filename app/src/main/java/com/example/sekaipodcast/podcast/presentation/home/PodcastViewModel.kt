package com.example.sekaipodcast.podcast.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sekaipodcast.player.service.Music
import com.example.sekaipodcast.player.service.PlayerHandlers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastViewModel @Inject constructor(
    private val playerHandlers: PlayerHandlers
) : ViewModel() {
    var isPlaying by mutableStateOf(false)
    var currentPosition by mutableStateOf(0L)
    var sliderPosition by mutableStateOf(0L)
    var totalDuration by mutableStateOf(0L)
    var playingSongIndex by mutableStateOf(0)

    init {
        // Initialize the player and state
        // Here we restore the playback state
        updatePlayerState()
        updatePlayerDuration()
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

