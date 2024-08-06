package com.example.sekaipodcast.auth.domain.use_case

import com.example.sekaipodcast.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadLoginEntryUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): Flow<String> {
        return repository.readLoginEntry()
    }
}