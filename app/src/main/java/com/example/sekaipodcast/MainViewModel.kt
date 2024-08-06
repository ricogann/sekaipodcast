package com.example.sekaipodcast

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sekaipodcast.auth.domain.use_case.ReadLoginEntryUseCase
import com.example.sekaipodcast.onboarding.domain.usecase.OnBoardingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel()
class MainViewModel @Inject constructor(
    private val onBoardingUseCases: OnBoardingUseCases,
    private val readLoginEntryUseCase: ReadLoginEntryUseCase
): ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.OnBoardingScreen.route)
        private set

    init {
        onBoardingUseCases.readAppEntry().onEach { shouldStartFromLoginScreen ->
            if (shouldStartFromLoginScreen) {
                readLoginEntryUseCase().onEach { token ->
                    if (token.isNotEmpty()) {
                        startDestination = Route.HomeScreen.route
                    }else {
                        startDestination = Route.LoginScreen.route
                    }
                }.launchIn(viewModelScope)
            }else {
                startDestination = Route.OnBoardingScreen.route
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}