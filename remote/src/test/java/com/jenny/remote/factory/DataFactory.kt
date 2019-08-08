package com.jenny.remote.factory

import com.jenny.domain.model.Episode
import com.jenny.domain.model.Seasons
import com.jenny.domain.model.TvShows
import com.jenny.domain.response.PopularTvShowResponse
import com.jenny.domain.response.SeasonDetailResponse
import com.jenny.domain.response.TvShowDetailResponse
import java.util.*

object DataFactory {
    fun getRandomString(): String {
        return UUID.randomUUID().toString()
    }

    fun getRandomInt(): Int {
        return (Math.random() * 1000).toInt()
    }

    fun getRandomDouble(): Double {
        return Math.random()
    }

    fun getRandomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun getRandomListOfInt(count: Int): List<Int> {
        val list = mutableListOf<Int>()
        repeat(count) {
            list.add(getRandomInt())
        }
        return list
    }

    fun getRandomTvShow(): TvShows {
        return TvShows(
            getRandomString(),
            getRandomString(),
            getRandomString(),
            getRandomInt()
        )
    }

    fun getTvShowList(): MutableList<TvShows> {
        val list = mutableListOf<TvShows>()
        repeat(5) {
            list.add(getRandomTvShow())
        }
        return list
    }

    fun getPopularTvShows(): PopularTvShowResponse {
        return PopularTvShowResponse(
            getRandomInt(),
            getTvShowList(),
            getRandomInt()
        )
    }

    fun getRandomSeason(): Seasons {
        return Seasons(
            getRandomString(),
            getRandomString(),
            getRandomString(),
            getRandomInt(),
            getRandomInt(),
            getRandomInt()
        )
    }

    fun getSeasonList(): MutableList<Seasons> {
        val list = mutableListOf<Seasons>()
        repeat(5) {
            list.add(getRandomSeason())
        }
        return list
    }

    fun getShowDetails(): TvShowDetailResponse {
        return TvShowDetailResponse(
            getSeasonList(),
            getRandomInt(),
            getRandomString(),
            getRandomString(),
            getRandomString(),
            getRandomInt()
        )
    }

    fun getRandomEpisode(): Episode {
        return Episode(
            getRandomString(),
            getRandomString(),
            getRandomString(),
            getRandomInt(),
            getRandomInt()
        )
    }

    fun getEpisodeList(): MutableList<Episode> {
        val list = mutableListOf<Episode>()
        repeat(5) {
            list.add(getRandomEpisode())
        }
        return list
    }

    fun getSeasonDetailResponse() : SeasonDetailResponse {
        return SeasonDetailResponse(
            getEpisodeList()
        )
    }
}