package com.jenny.domain.response

import com.jenny.domain.model.Episode

data class SeasonDetailResponse(
    val episodes: MutableList<Episode>
)