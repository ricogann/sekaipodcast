package com.example.sekaipodcast.profile.presentation.profile

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT
import com.example.sekaipodcast.Route
import com.example.sekaipodcast.auth.domain.use_case.ReadLoginEntryUseCase
import com.example.sekaipodcast.common.Resource
import com.example.sekaipodcast.profile.data.remote.dto.DataMutual
import com.example.sekaipodcast.profile.data.remote.dto.DataUser
import com.example.sekaipodcast.profile.data.remote.dto.Podcast
import com.example.sekaipodcast.profile.domain.use_case.DeletePodcastUseCase
import com.example.sekaipodcast.profile.domain.use_case.GetDetailUserUseCase
import com.example.sekaipodcast.profile.domain.use_case.LogoutUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.json.JSONObject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val readLoginEntryUseCase: ReadLoginEntryUseCase,
    private val getDetailUserUseCase: GetDetailUserUseCase,
    private val deletePodcastUseCase: DeletePodcastUseCase,
    private val logoutUseCase: LogoutUseCase
): ViewModel() {
    var id by mutableStateOf<String>("")
        private set

    var user by mutableStateOf<DataUser?>(null)
        private set

    var podcasts by mutableStateOf<List<Podcast>?>(null)
        private set

    var following by mutableStateOf<List<DataMutual>?>(null)
        private set

    var followers by mutableStateOf<List<DataMutual>?>(null)
        private set

    var statusDelete by mutableStateOf<Boolean>(false)
        private set

    init {
        readLoginEntryUseCase().onEach { token ->
            if (token.isNotEmpty()) {
                val res = parseJWT(token)
                id = res.getString("id")
                fetchDataUserDetail(id)
            }
        }.launchIn(viewModelScope)
    }

    private fun parseJWT(token: String): JSONObject {
        val decodedJWT: DecodedJWT = JWT.decode(token)
        val payload = decodedJWT.payload
        val decodedBytes = java.util.Base64.getDecoder().decode(payload)
        val decodedString = String(decodedBytes)
        return JSONObject(decodedString)
    }

    private fun fetchDataUserDetail(id: String) {
        viewModelScope.launch {
            getDetailUserUseCase(id).collect { resource ->
                when (resource) {
                    is Resource.Loading -> Log.d("testing", "Loading")
                    is Resource.Success -> {
                        user = resource.data?.data
                        podcasts = resource.data?.data?.podcast
                        following = resource.data?.data?.following
                        followers = resource.data?.data?.followers
                    }
                    is Resource.Error -> Log.d("testing", "Error: ${resource.message}")
                }
            }
        }
    }

    fun deletePodcast(idPodcast: String) {
        viewModelScope.launch {
            deletePodcastUseCase(idPodcast).collect { resource ->
                when (resource) {
                    is Resource.Loading -> Log.d("testing", "Loading")
                    is Resource.Success -> {
                        statusDelete = resource.data?.status ?: false
                        if (statusDelete) {
                            fetchDataUserDetail(id)
                        }
                    }
                    is Resource.Error -> Log.d("testing", "Error: ${resource.message}")
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }
}
