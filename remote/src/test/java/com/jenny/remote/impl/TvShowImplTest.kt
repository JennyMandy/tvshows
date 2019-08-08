package com.jenny.remote.impl

import com.jenny.domain.response.PopularTvShowResponse
import com.jenny.domain.response.SeasonDetailResponse
import com.jenny.domain.response.TvShowDetailResponse
import com.jenny.remote.factory.DataFactory
import com.jenny.remote.service.TvShowApiService
import com.jenny.remote.service.TvShowApiServiceFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class TvShowImplTest {
    val API_KEY = "5d4336fa4e3d9b8aadfdccc95042ac42"

    private val serviceFactory = Mockito.mock(TvShowApiServiceFactory::class.java)
    private val tvShowApiService = Mockito.mock(TvShowApiService::class.java)
    private val tvShowApiServiceImpl = TvShowImpl(tvShowApiService)

    @Before
    fun setup() {
        stubGetService()
    }

    private fun stubGetService() {
        Mockito.`when`(serviceFactory.getTvShowApiService()).thenReturn(tvShowApiService)
    }

    private fun stubGetLatestTvShows(single: Single<PopularTvShowResponse>, pageNo: Int) {
        Mockito.`when`(tvShowApiService.getLatestTvShows(API_KEY, pageNo)).thenReturn(single)
    }

    private fun stubGetTvShowDetails(single: Single<TvShowDetailResponse>, tvId: Int) {
        Mockito.`when`(tvShowApiService.getTvShowDetails(tvId, API_KEY)).thenReturn(single)
    }

    private fun stubGetSeasonDetails(single: Single<SeasonDetailResponse>, tvId: Int, seasonNo: Int) {
        Mockito.`when`(tvShowApiService.getSeasonDetails(tvId, seasonNo, API_KEY)).thenReturn(single)
    }

    @Test
    fun getLatestTvShowsCompletesTest() {
        val pageNo = 1
        val response = DataFactory.getPopularTvShows()
        stubGetLatestTvShows(Single.just(response), pageNo)
        val testObserver = tvShowApiServiceImpl.getLatestTvShows(pageNo).test()
        testObserver.assertComplete()
    }

    @Test
    fun getTvShowDetailsCompletesTest() {
        val tvId = 1
        val response = DataFactory.getShowDetails()
        stubGetTvShowDetails(Single.just(response), tvId)
        val testObserver = tvShowApiServiceImpl.getTvShowDetails(tvId).test()
        testObserver.assertComplete()
    }

    @Test
    fun getSeasonDetailsCompletesTest() {
        val tvId = 1
        val seasonNo = 2
        val response = DataFactory.getSeasonDetailResponse()
        stubGetSeasonDetails(Single.just(response), tvId, seasonNo)
        val testObserver = tvShowApiServiceImpl.getSeasonDetails(tvId, seasonNo).test()
        testObserver.assertComplete()
    }
}