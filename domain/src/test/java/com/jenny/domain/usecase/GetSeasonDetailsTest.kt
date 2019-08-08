package com.jenny.domain.usecase

import com.jenny.domain.repository.TvShowRepository
import com.jenny.domain.response.SeasonDetailResponse
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
class GetSeasonDetailsTest {
    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    private lateinit var getSeasonDetails: GetSeasonDetails

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getSeasonDetails = GetSeasonDetails(tvShowRepository, postExecutionThread)
    }

    private fun stubGetSeasonDetails(single: Single<SeasonDetailResponse>, tvId: Int, seasonNo: Int) {
        Mockito.`when`(tvShowRepository.getSeasonDetails(tvId, seasonNo)).thenReturn(single)
    }

    @Test
    fun getSearchedMoviesCompletesTest() {
        val tvId = DataFactory.getRandomInt()
        val seasonNo = DataFactory.getRandomInt()
        val seasonDetailResponse = DataFactory.getSeasonDetailResponse()
        stubGetSeasonDetails(Single.just(seasonDetailResponse), tvId, seasonNo)
        val testObserver =
            getSeasonDetails.buildUseCaseObservable(GetSeasonDetails.Params.getParams(tvId, seasonNo)).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun getSearchedMoviesThrowsErrorIfTvIdIsZeroTest() {
        val tvId = 0
        val seasonNo = DataFactory.getRandomInt()
        val seasonDetailResponse = DataFactory.getSeasonDetailResponse()
        stubGetSeasonDetails(Single.just(seasonDetailResponse), tvId, seasonNo)
        getSeasonDetails.buildUseCaseObservable(GetSeasonDetails.Params.getParams(tvId, seasonNo)).test()
    }

    @Test(expected = IllegalArgumentException::class)
    fun getSearchedMoviesThrowsErrorIfParamsIsNullTest() {
        val tvId = DataFactory.getRandomInt()
        val seasonNo = DataFactory.getRandomInt()
        val seasonDetailResponse = DataFactory.getSeasonDetailResponse()
        stubGetSeasonDetails(Single.just(seasonDetailResponse), tvId, seasonNo)
        getSeasonDetails.buildUseCaseObservable(null).test()
    }

    @Test(expected = IllegalArgumentException::class)
    fun getSearchedMoviesThrowsErrorIfObserverIsNullTest() {
        getSeasonDetails = GetSeasonDetails(tvShowRepository, postExecutionThread)
        getSeasonDetails.execute(null)
    }
}