package com.jenny.data.impl

import com.jenny.data.store.TvShowsDataStoreFactory
import com.jenny.domain.repository.TvShowRepository
import com.jenny.domain.response.PopularTvShowResponse
import com.jenny.domain.response.SeasonDetailResponse
import com.jenny.domain.response.TvShowDetailResponse
import io.reactivex.Single
import javax.inject.Inject

class TvShowDataRepositoryImpl @Inject
constructor(private val tvShowsDataStoreFactory: TvShowsDataStoreFactory) : TvShowRepository {
    override fun getLatestTvShows(pageNo: Int): Single<PopularTvShowResponse> {
        return tvShowsDataStoreFactory.tvShowsRemoteDataStore.getLatestTvShows(pageNo)
    }

    override fun getTvShowDetails(tvId: Int): Single<TvShowDetailResponse> {
        return tvShowsDataStoreFactory.tvShowsRemoteDataStore.getTvShowDetails(tvId)
    }

    override fun getSeasonDetails(tvId: Int, seasonNumber: Int): Single<SeasonDetailResponse> {
        return tvShowsDataStoreFactory.tvShowsRemoteDataStore.getSeasonDetails(tvId, seasonNumber)
    }
}