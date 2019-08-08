package com.jenny.domain.response

import com.jenny.domain.model.TvShows

data class PopularTvShowResponse(
    val page: Int,
    val results: MutableList<TvShows>,
    val total_pages: Int
)