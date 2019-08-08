package com.jenny.tvshows.modules

import com.jenny.domain.usecase.PostExecutionThread
import com.jenny.tvshows.UIThread
import com.jenny.tvshows.activity.ActivitySeasonDetail
import com.jenny.tvshows.activity.ActivityTvShowDetail
import com.jenny.tvshows.activity.ActivityTvShows
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UIModule {
    @Binds
    abstract fun bindsPostExecutionThread(uiThread: UIThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesActivityTvShows(): ActivityTvShows

    @ContributesAndroidInjector
    abstract fun contributesActivityTvShowDetail(): ActivityTvShowDetail

    @ContributesAndroidInjector
    abstract fun contributesActivitySeasonDetail(): ActivitySeasonDetail
}