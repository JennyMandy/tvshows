package com.jenny.tvshows.modules

import com.jenny.data.impl.TvShowDataRepositoryImpl
import com.jenny.data.repository.TvShowRemote
import com.jenny.remote.impl.TvShowImpl
import com.jenny.remote.service.TvShowApiService
import com.jenny.remote.service.TvShowApiServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RemoteModule {
    @Module
    companion object {
        @Provides
        @JvmStatic
        @Singleton
        fun providesTvShowApiService(): TvShowApiService {
            return TvShowApiServiceFactory.getTvShowApiService()
        }
    }

    @Binds
    abstract fun bindsTvShowRemote(tvShowImpl: TvShowImpl): TvShowRemote
}