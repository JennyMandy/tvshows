package com.jenny.remote.service

import com.jenny.domain.response.PopularTvShowResponse
import com.jenny.domain.response.SeasonDetailResponse
import com.jenny.domain.response.TvShowDetailResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApiService {
    @GET("tv/popular")
    fun getLatestTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<PopularTvShowResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetails(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): Single<TvShowDetailResponse>

    @GET("tv/{tv_id}/season/{season_number}")
    fun getSeasonDetails(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("api_key") apiKey: String
    ): Single<SeasonDetailResponse>
}