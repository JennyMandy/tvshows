package com.jenny.domain.model

data class Episode(
    val name: String,
    val poster_path: String,
    val overview: String,
    val id: Int,
    val season_number: Int
)