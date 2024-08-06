package com.example.sekaipodcast.profile.domain.use_case

import com.example.sekaipodcast.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke() {
        return repository.logout()
    }
}