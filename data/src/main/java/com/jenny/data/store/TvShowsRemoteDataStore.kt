package com.jenny.data.store

import com.jenny.data.repository.TvShowDataStore
import com.jenny.data.repository.TvShowRemote
import com.jenny.domain.response.PopularTvShowResponse
import com.jenny.domain.response.SeasonDetailResponse
import com.jenny.domain.response.TvShowDetailResponse
import io.reactivex.Single
import javax.inject.Inject

class TvShowsRemoteDataStore @Inject
constructor(val tvShowRemote: TvShowRemote) : TvShowDataStore {
    override fun getTvShowDetails(tvId: Int): Single<TvShowDetailResponse> {
        return tvShowRemote.getTvShowDetails(tvId)
    }

    override fun getSeasonDetails(tvId: Int, seasonNumber: Int): Single<SeasonDetailResponse> {
        return tvShowRemote.getSeasonDetails(tvId, seasonNumber)
    }

    override fun getLatestTvShows(pageNo: Int): Single<PopularTvShowResponse> {
        return tvShowRemote.getLatestTvShows(pageNo)
    }

}