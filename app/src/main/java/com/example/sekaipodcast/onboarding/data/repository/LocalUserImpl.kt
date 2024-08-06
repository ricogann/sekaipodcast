package com.example.sekaipodcast.onboarding.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.sekaipodcast.onboarding.domain.repository.LocalUser
import com.example.sekaipodcast.onboarding.util.Constants
import com.example.sekaipodcast.onboarding.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserImpl (
    private val dataStore: DataStore<Preferences>
): LocalUser {
    override suspend fun saveAppEntry() {
        dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY]?:false
        }
    }
}

private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
}