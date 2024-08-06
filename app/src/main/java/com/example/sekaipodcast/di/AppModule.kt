package com.example.sekaipodcast.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import com.example.sekaipodcast.auth.data.remote.AuthAPI
import com.example.sekaipodcast.auth.data.repository.AuthRepositoryImpl
import com.example.sekaipodcast.auth.domain.repository.AuthRepository
import com.example.sekaipodcast.auth.domain.use_case.ReadLoginEntryUseCase
import com.example.sekaipodcast.auth.domain.use_case.SaveLoginEntryUseCase
import com.example.sekaipodcast.common.Constants
import com.example.sekaipodcast.onboarding.data.repository.LocalUserImpl
import com.example.sekaipodcast.onboarding.domain.repository.LocalUser
import com.example.sekaipodcast.onboarding.domain.usecase.OnBoardingUseCases
import com.example.sekaipodcast.onboarding.domain.usecase.ReadAppEntry
import com.example.sekaipodcast.onboarding.domain.usecase.SaveAppEntry
import com.example.sekaipodcast.player.service.PlayerHandlers
import com.example.sekaipodcast.playlist.data.remote.PlaylistAPI
import com.example.sekaipodcast.playlist.data.repository.PlaylistRepositoryImpl
import com.example.sekaipodcast.playlist.domain.repository.PlaylistRepository
import com.example.sekaipodcast.podcast.data.remote.PodcastAPI
import com.example.sekaipodcast.podcast.data.repository.PodcastRepositoryImpl
import com.example.sekaipodcast.podcast.domain.repository.PodcastRepository
import com.example.sekaipodcast.profile.data.remote.ProfileAPI
import com.example.sekaipodcast.profile.data.repository.ProfileRepositoryImpl
import com.example.sekaipodcast.profile.domain.repository.ProfileRepository
import com.example.sekaipodcast.profile.domain.use_case.LogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAudioAttributes(): AudioAttributes = AudioAttributes.Builder()
        .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
        .setUsage(C.USAGE_MEDIA)
        .build()

    @Provides
    @Singleton
    @UnstableApi
    fun provideExoPlayer(
        @ApplicationContext context: Context,
        audioAttributes: AudioAttributes
    ): ExoPlayer = ExoPlayer.Builder(context)
        .setAudioAttributes(audioAttributes, true)
        .setHandleAudioBecomingNoisy(true)
        .setTrackSelector(DefaultTrackSelector(context))
        .build()

    @Provides
    @Singleton
    fun providePlayerHandlers(
        @ApplicationContext context: Context,
        exoPlayer: ExoPlayer
    ): PlayerHandlers = PlayerHandlers(context, exoPlayer)



//    @Provides
//    @Singleton
//    fun provideMediaSession(
//        @ApplicationContext context: Context,
//        player: ExoPlayer,
//    ): MediaSession = MediaSession.Builder(context, player).build()


    @Provides
    @Singleton
    fun provideLocalUser(
        dataStore: DataStore<Preferences>
    ): LocalUser = LocalUserImpl(dataStore)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUser: LocalUser
    ) = OnBoardingUseCases(
        readAppEntry = ReadAppEntry(localUser),
        saveAppEntry = SaveAppEntry(localUser)
    )

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthAPI(okHttpClient: OkHttpClient): AuthAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_DEV)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthAPI, dataStore: DataStore<Preferences>): AuthRepository {
        return AuthRepositoryImpl(api, dataStore)
    }

    @Provides
    @Singleton
    fun provideSaveLoginEntryUseCase(
        authRepository: AuthRepository
    ): SaveLoginEntryUseCase {
        return SaveLoginEntryUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideReadLoginEntryUseCase(
        authRepository: AuthRepository
    ): ReadLoginEntryUseCase {
        return ReadLoginEntryUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun providePodcastAPI(okHttpClient: OkHttpClient): PodcastAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_DEV)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PodcastAPI::class.java)
    }

    @Provides
    @Singleton
    fun providePodcastRepository(api: PodcastAPI): PodcastRepository {
        return PodcastRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providePlaylistAPI(okHttpClient: OkHttpClient): PlaylistAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_DEV)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlaylistAPI::class.java)
    }

    @Provides
    @Singleton
    fun providePlaylistRepository(api: PlaylistAPI): PlaylistRepository {
        return PlaylistRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideProfileAPI(okHttpClient: OkHttpClient): ProfileAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_DEV)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProfileAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(api: ProfileAPI, dataStore: DataStore<Preferences>): ProfileRepository {
        return ProfileRepositoryImpl(api, dataStore)
    }

    @Provides
    @Singleton
    fun provideLogoutUseCase(
        profileRepository: ProfileRepository
    ): LogoutUseCase {
        return LogoutUseCase(profileRepository)
    }

//    @Provides
//    @Singleton
//    fun provideMediaSession(
//        @ApplicationContext context: Context,
//        player: ExoPlayer,
//    ): MediaSession = MediaSession.Builder(context, player).build()
}