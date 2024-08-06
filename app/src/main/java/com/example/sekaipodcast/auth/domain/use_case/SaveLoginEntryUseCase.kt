package com.example.sekaipodcast.auth.domain.use_case

import com.example.sekaipodcast.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveLoginEntryUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(token: String) {
        return repository.saveLoginEntry(token)
    }
}