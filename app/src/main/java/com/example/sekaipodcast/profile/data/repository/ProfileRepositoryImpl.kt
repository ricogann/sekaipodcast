package com.example.sekaipodcast.profile.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.sekaipodcast.profile.data.repository.PreferencesKeys
import com.example.sekaipodcast.onboarding.util.Constants
import com.example.sekaipodcast.profile.data.remote.ProfileAPI
import com.example.sekaipodcast.profile.data.remote.dto.ResponseCountry
import com.example.sekaipodcast.profile.data.remote.dto.ResponseDeletePodcast
import com.example.sekaipodcast.profile.data.remote.dto.ResponseFollowInfo
import com.example.sekaipodcast.profile.data.remote.dto.ResponseGetUser
import com.example.sekaipodcast.profile.domain.model.FollowInfo
import com.example.sekaipodcast.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api: ProfileAPI,
    private val dataStore: DataStore<Preferences>
): ProfileRepository {
    override suspend fun getCountry(): ResponseCountry {
        return api.getCountry()
    }

    override suspend fun getDetailUser(id: String): ResponseGetUser {
        return api.getDetailUser(id)
    }

    override suspend fun deletePodcast(id: String): ResponseDeletePodcast {
        return api.deletePodcast(id)
    }

    override suspend fun logout() {
        dataStore.edit { settings ->
            settings[PreferencesKeys.LOGIN_ENTRY] = ""
        }
    }

    override suspend fun followInfo(requestBody: FollowInfo): ResponseFollowInfo {
        return api.followInfo(requestBody)
    }

    override suspend fun follow(requestBody: FollowInfo): ResponseFollowInfo {
        return api.follow(requestBody)
    }

    override suspend fun unfollow(requestBody: FollowInfo): ResponseFollowInfo {
        return api.unfollow(requestBody)
    }
}

private object PreferencesKeys {
    val LOGIN_ENTRY = stringPreferencesKey(Constants.LOGIN_ENTRY)
}