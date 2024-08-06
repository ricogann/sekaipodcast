package com.example.sekaipodcast.profile.presentation.editprofile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.profile.data.remote.dto.DataCountry
import com.example.sekaipodcast.profile.data.remote.dto.DataUser
import com.example.sekaipodcast.profile.data.remote.dto.Podcast
import com.example.sekaipodcast.profile.domain.use_case.GetCountryUseCase
import com.example.sekaipodcast.profile.domain.use_case.GetDetailUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryUseCase,
    private val getDetailUserUseCase: GetDetailUserUseCase
): ViewModel() {
    var countries by mutableStateOf<List<DataCountry>?>(null)
        private set

    var dataUser by mutableStateOf<DataUser?>(null)
        private set

    var podcasts by mutableStateOf<List<Podcast>?>(null)
        private set


    init {
        fetchCountryData()
    }

    private fun fetchCountryData() {
        viewModelScope.launch {
            getCountryUseCase().collect { resource ->
                when (resource) {
                    is Resource.Loading -> Log.d("testing", "Loading")
                    is Resource.Success -> {
                        countries = resource.data?.data
                    }
                    is Resource.Error -> Log.d("testing", "Error: ${resource.message}")
                }
            }
        }
    }

    private fun fetchUserData(profileId: String) {
        viewModelScope.launch {
            getDetailUserUseCase(profileId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> Log.d("testing", "Loading")
                    is Resource.Success -> {
                        dataUser = resource.data?.data
                        podcasts = resource.data?.data?.podcast
                    }
                    is Resource.Error -> Log.d("testing", "Error: ${resource.message}")
                }
            }
        }
    }
}