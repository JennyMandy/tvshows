package com.jenny.domain.usecase

import com.jenny.domain.repository.TvShowRepository
import com.jenny.domain.response.TvShowDetailResponse
import io.reactivex.Single
import javax.inject.Inject

class GetTvShowsDetails
@Inject
constructor(private val tvShowRepository: TvShowRepository, postExecutionThread: PostExecutionThread) :
    SingleUseCase<TvShowDetailResponse, GetTvShowsDetails.Params>(postExecutionThread) {
    override fun buildUseCaseObservable(params: Params?): Single<TvShowDetailResponse> {
        params?.let {
            if (params.tvId == 0) {
                throw IllegalArgumentException("Tv Id cannot be 0")
            }
            return tvShowRepository.getTvShowDetails(params.tvId)
        }
        throw IllegalArgumentException()
    }

    data class Params constructor(val tvId: Int) {
        companion object {
            fun getParams(tvId: Int) = Params(tvId)
        }
    }
}