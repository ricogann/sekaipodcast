package com.example.sekaipodcast.auth.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.sekaipodcast.auth.data.remote.AuthAPI
import com.example.sekaipodcast.auth.domain.model.Login
import com.example.sekaipodcast.auth.domain.model.Register
import com.example.sekaipodcast.auth.data.remote.dto.Response
import com.example.sekaipodcast.auth.domain.repository.AuthRepository
import com.example.sekaipodcast.onboarding.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthAPI,
    private val dataStore: DataStore<Preferences>
): AuthRepository {
    override suspend fun register(requestBody: Register): Response {
        return api.registerAccount(requestBody)
    }

    override suspend fun login(requestBody: Login): Response {
        return api.loginAccount(requestBody)
    }

    override suspend fun saveLoginEntry(token: String) {
        dataStore.edit { settings ->
            settings[PreferencesKeys.LOGIN_ENTRY] = token
        }
    }

    override fun readLoginEntry(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKeys.LOGIN_ENTRY] ?: ""
        }
    }
}

private object PreferencesKeys {
    val LOGIN_ENTRY = stringPreferencesKey(Constants.LOGIN_ENTRY)
}