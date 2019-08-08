package com.jenny.data.repository

import com.jenny.domain.response.PopularTvShowResponse
import com.jenny.domain.response.SeasonDetailResponse
import com.jenny.domain.response.TvShowDetailResponse
import io.reactivex.Single

interface TvShowDataStore {
    fun getLatestTvShows(pageNo: Int): Single<PopularTvShowResponse>
    fun getTvShowDetails(tvId: Int): Single<TvShowDetailResponse>
    fun getSeasonDetails(tvId: Int, seasonNumber: Int): Single<SeasonDetailResponse>
}