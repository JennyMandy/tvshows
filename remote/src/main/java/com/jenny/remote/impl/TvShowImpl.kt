package com.jenny.remote.impl

import com.jenny.data.repository.TvShowRemote
import com.jenny.domain.response.PopularTvShowResponse
import com.jenny.domain.response.SeasonDetailResponse
import com.jenny.domain.response.TvShowDetailResponse
import com.jenny.remote.service.TvShowApiService
import io.reactivex.Single
import javax.inject.Inject

class TvShowImpl
@Inject
constructor(private val tvShowApiService: TvShowApiService) : TvShowRemote {
    val API_KEY = "5d4336fa4e3d9b8aadfdccc95042ac42"
    override fun getLatestTvShows(pageNo: Int): Single<PopularTvShowResponse> {
        return tvShowApiService.getLatestTvShows(API_KEY, pageNo)
    }

    override fun getTvShowDetails(tvId: Int): Single<TvShowDetailResponse> {
        return tvShowApiService.getTvShowDetails(tvId, API_KEY)
    }

    override fun getSeasonDetails(tvId: Int, seasonNumber: Int): Single<SeasonDetailResponse> {
        return tvShowApiService.getSeasonDetails(tvId, seasonNumber, API_KEY)
    }
}