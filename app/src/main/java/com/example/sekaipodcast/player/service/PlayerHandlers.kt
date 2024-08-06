package com.example.sekaipodcast.player.service

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerHandlers @Inject constructor(
    private val context: Context,
    val exoPlayer: ExoPlayer
): Player.Listener {

    init {
        exoPlayer.addListener(this)
    }

    fun initPlayer(playList: List<Music>) {
        playList.forEach {
            val mediaItem = MediaItem.fromUri(Uri.parse(it.url))
            exoPlayer.addMediaItem(mediaItem)
        }
        exoPlayer.prepare()
    }

    fun playPause() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
        } else {
            exoPlayer.play()
        }
    }

    fun seekToNext() {
        exoPlayer.seekToNextMediaItem()
    }

    fun seekToPrevious() {
        exoPlayer.seekToPreviousMediaItem()
    }

    fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
    }

    fun getCurrentPosition(): Long = exoPlayer.currentPosition
    fun getDuration(): Long = exoPlayer.duration
    fun getCurrentMediaIndex(): Int = exoPlayer.currentMediaItemIndex
    fun isPlaying(): Boolean = exoPlayer.isPlaying
}


data class Music(
    val name: String,
    val artist: String,
    val music: Int,
    val url: String,
)