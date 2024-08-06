package com.example.sekaipodcast.podcast.presentation.addpodcast

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.podcast.data.remote.dto.DataCountry
import com.example.sekaipodcast.podcast.data.remote.dto.DataLevel
import com.example.sekaipodcast.podcast.domain.model.UploadPodcastRequest
import com.example.sekaipodcast.podcast.domain.use_case.GetCountryUseCase
import com.example.sekaipodcast.podcast.domain.use_case.GetLevelUseCase
import com.example.sekaipodcast.podcast.domain.use_case.UploadPodcastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class AddPodcastViewModel @Inject constructor(
    private val countryUseCase: GetCountryUseCase,
    private val levelUseCase: GetLevelUseCase,
    private val uploadPodcastUseCase: UploadPodcastUseCase
): ViewModel() {
    var countries by mutableStateOf<List<DataCountry>?>(null)
        private set

    var level by mutableStateOf<List<DataLevel>?>(null)
        private set

    var statusUpload by mutableStateOf(false)


    init {
        Log.d("testing", "ViewModel initialized")
        fetchCountryData()
    }

    private fun fetchCountryData() {
        viewModelScope.launch {
            countryUseCase().collect { resource ->
                when (resource) {
                    is Resource.Loading -> Log.d("testing", "Loading")
                    is Resource.Success -> {
                        countries = resource.data?.data
                    }
                    is Resource.Error -> Log.d("testing", "Error: ${resource.message}")
                }
            }
            levelUseCase().collect { resource ->
                when (resource) {
                    is Resource.Loading -> Log.d("testing", "Loading")
                    is Resource.Success -> {
                        level = resource.data?.data
                    }
                    is Resource.Error -> Log.d("testing", "Error: ${resource.message}")
                }
            }
        }
    }

    fun postUploadPodcast(
        title: RequestBody,
        description: RequestBody,
        country: RequestBody,
        level: RequestBody,
        account: RequestBody,
        thumbnail: MultipartBody.Part?,
        podcast: MultipartBody.Part?
    ) {
        viewModelScope.launch {
            uploadPodcastUseCase(title, description, country, level, account, thumbnail, podcast).collect { resource ->
                when (resource) {
                    is Resource.Loading -> Log.d("uploadPodcast", "Loading")
                    is Resource.Success -> statusUpload = resource.data?.status == true
                    is Resource.Error -> Log.d("uploadPodcast", "Error: ${resource.message}")
                }
            }
        }
    }
}