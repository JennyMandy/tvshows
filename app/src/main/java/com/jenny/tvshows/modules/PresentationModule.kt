package com.jenny.tvshows.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jenny.presentation.viewmodel.GetSeasonDetailsViewModel
import com.jenny.presentation.viewmodel.GetTvShowDetailsViewModel
import com.jenny.presentation.viewmodel.GetTvShowsViewModel
import com.jenny.tvshows.dependencies.ViewModelFactory
import com.jenny.tvshows.dependencies.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(GetTvShowsViewModel::class)
    abstract fun bindsGetTvShowsViewModel(getTvShowsViewModel: GetTvShowsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GetTvShowDetailsViewModel::class)
    abstract fun bindsGetTvShowDetailsViewModel(getTvShowsViewModel: GetTvShowDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GetSeasonDetailsViewModel::class)
    abstract fun bindsGetSeasonDetailsViewModel(getTvShowsViewModel: GetSeasonDetailsViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}