package com.jenny.domain.response

import com.jenny.domain.model.Seasons

data class TvShowDetailResponse(
    val seasons: MutableList<Seasons>,
    val total_pages: Int,
    val backdrop_path: String,
    val name: String,
    val overview: String,
    val number_of_seasons: Int
    )