package com.example.sekaipodcast.onboarding.domain.usecase

import com.example.sekaipodcast.onboarding.domain.repository.LocalUser
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUser: LocalUser
) {
    operator fun invoke(): Flow<Boolean> {
        return localUser.readAppEntry()
    }
}