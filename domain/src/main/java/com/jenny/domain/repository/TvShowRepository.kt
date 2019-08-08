package com.jenny.domain.repository

import com.jenny.domain.response.PopularTvShowResponse
import com.jenny.domain.response.SeasonDetailResponse
import com.jenny.domain.response.TvShowDetailResponse
import io.reactivex.Single

interface TvShowRepository {
    fun getLatestTvShows(pageNo: Int): Single<PopularTvShowResponse>
    fun getTvShowDetails(tvId: Int): Single<TvShowDetailResponse>
    fun getSeasonDetails(tvId: Int, seasonNumber: Int): Single<SeasonDetailResponse>
}