package com.jenny.tvshows.modules

import com.jenny.data.impl.TvShowDataRepositoryImpl
import com.jenny.domain.repository.TvShowRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {
    @Binds
    abstract fun bindsTvShowDataRepositoryImpl(tvShowDataRepositoryImpl: TvShowDataRepositoryImpl): TvShowRepository
}