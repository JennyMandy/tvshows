package com.jenny.domain.usecase

import com.jenny.domain.repository.TvShowRepository
import com.jenny.domain.response.SeasonDetailResponse
import io.reactivex.Single
import javax.inject.Inject

class GetSeasonDetails
@Inject
constructor(private val tvShowRepository: TvShowRepository, postExecutionThread: PostExecutionThread) :
    SingleUseCase<SeasonDetailResponse, GetSeasonDetails.Params>(postExecutionThread) {
    override fun buildUseCaseObservable(params: Params?): Single<SeasonDetailResponse> {
        params?.let {
            if (params.tvId == 0) {
                throw IllegalArgumentException("Tv Id cannot be 0")
            }
            return tvShowRepository.getSeasonDetails(params.tvId, params.seasonNumber)
        }
        throw IllegalArgumentException()
    }

    data class Params constructor(val tvId: Int, val seasonNumber: Int) {
        companion object {
            fun getParams(tvId: Int, seasonNumber: Int) = Params(tvId, seasonNumber)
        }
    }
}