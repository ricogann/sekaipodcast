package com.example.sekaipodcast.auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sekaipodcast.auth.data.remote.dto.Response
import com.example.sekaipodcast.auth.domain.model.Login
import com.example.sekaipodcast.auth.domain.use_case.LoginUseCase
import com.example.sekaipodcast.auth.domain.use_case.SaveLoginEntryUseCase
import com.example.sekaipodcast.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveLoginEntryUseCase: SaveLoginEntryUseCase
): ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun login(requestBody: Login) {
        loginUseCase(requestBody).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = LoginState(
                        response = result.data ?: Response()
                    )

                    result.data?.let {
                        viewModelScope.launch {
                            saveLoginEntryUseCase(it.token)
                        }
                    }
                }

                is Resource.Error -> {
                    _state.value = LoginState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = LoginState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}