package com.jenny.tvshows

import android.app.Application
import com.jenny.tvshows.modules.DataModule
import com.jenny.tvshows.modules.PresentationModule
import com.jenny.tvshows.modules.RemoteModule
import com.jenny.tvshows.modules.UIModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        PresentationModule::class,
        DataModule::class,
        RemoteModule::class,
        UIModule::class]
)
interface ApplicationComponent {
    fun inject(app: TvShowsApplication)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}