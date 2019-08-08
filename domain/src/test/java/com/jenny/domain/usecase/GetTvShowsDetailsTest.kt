package com.jenny.domain.usecase

import com.jenny.domain.repository.TvShowRepository
import com.jenny.domain.response.PopularTvShowResponse
import com.jenny.domain.response.TvShowDetailResponse
import com.jenny.remote.factory.DataFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GetTvShowsDetailsTest {
    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    private lateinit var getTvShowsDetails: GetTvShowsDetails

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getTvShowsDetails = GetTvShowsDetails(tvShowRepository, postExecutionThread)
    }

    private fun stubGetTvShowDetails(single: Single<TvShowDetailResponse>, tvId: Int) {
        Mockito.`when`(tvShowRepository.getTvShowDetails(tvId)).thenReturn(single)
    }

    @Test
    fun getSearchedMoviesCompletesTest() {
        val tvId = DataFactory.getRandomInt()
        val tvShowResponse = DataFactory.getShowDetails()
        stubGetTvShowDetails(Single.just(tvShowResponse), tvId)
        val testObserver = getTvShowsDetails.buildUseCaseObservable(GetTvShowsDetails.Params.getParams(tvId)).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun getSearchedMoviesThrowsErrorIfTvIdIsZeroTest() {
        val tvId = 0
        val showDetails = DataFactory.getShowDetails()
        stubGetTvShowDetails(Single.just(showDetails), tvId)
        getTvShowsDetails.buildUseCaseObservable(GetTvShowsDetails.Params.getParams(tvId)).test()
    }

    @Test(expected = IllegalArgumentException::class)
    fun getSearchedMoviesThrowsErrorIfParamsIsNullTest() {
        val tvId = DataFactory.getRandomInt()
        val showDetails = DataFactory.getShowDetails()
        stubGetTvShowDetails(Single.just(showDetails), tvId)
        getTvShowsDetails.buildUseCaseObservable(null).test()
    }

    @Test(expected = IllegalArgumentException::class)
    fun getSearchedMoviesThrowsErrorIfObserverIsNullTest() {
        getTvShowsDetails = GetTvShowsDetails(tvShowRepository, postExecutionThread)
        getTvShowsDetails.execute(null)
    }
}