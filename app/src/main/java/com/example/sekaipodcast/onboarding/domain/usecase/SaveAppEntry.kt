package com.example.sekaipodcast.onboarding.domain.usecase

import com.example.sekaipodcast.onboarding.domain.repository.LocalUser

class SaveAppEntry(
    private val localUser: LocalUser
) {
    suspend operator fun invoke() {
        localUser.saveAppEntry()
    }
}