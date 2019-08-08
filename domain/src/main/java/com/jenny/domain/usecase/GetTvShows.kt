package com.jenny.domain.usecase

import com.jenny.domain.repository.TvShowRepository
import com.jenny.domain.response.PopularTvShowResponse
import io.reactivex.Single
import javax.inject.Inject

class GetTvShows
@Inject
constructor(private val tvShowRepository: TvShowRepository, postExecutionThread: PostExecutionThread) :
    SingleUseCase<PopularTvShowResponse, GetTvShows.Params>(postExecutionThread) {
    override fun buildUseCaseObservable(params: Params?): Single<PopularTvShowResponse> {
        params?.let {
            if (params.pageNo == 0) {
                throw IllegalArgumentException("Page number cannot be 0")
            }
            return tvShowRepository.getLatestTvShows(params.pageNo)
        }
        throw IllegalArgumentException()
    }

    data class Params constructor(val pageNo: Int) {
        companion object {
            fun getParams(pageNo: Int) = Params(pageNo)
        }
    }
}