package com.example.sekaipodcast.auth.presentation.register

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sekaipodcast.auth.data.remote.dto.Response
import com.example.sekaipodcast.auth.domain.model.Register
import com.example.sekaipodcast.auth.domain.use_case.RegisterUseCase
import com.example.sekaipodcast.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
): ViewModel() {
    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    fun register(requestBody: Register) {
        registerUseCase(requestBody).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = RegisterState(
                        response = result.data ?: Response()
                    )
                }

                is Resource.Error -> {
                    _state.value = RegisterState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = RegisterState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
