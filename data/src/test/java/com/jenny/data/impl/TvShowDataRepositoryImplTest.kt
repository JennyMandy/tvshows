package com.jenny.data.impl

import com.jenny.data.store.TvShowsDataStoreFactory
import com.jenny.data.store.TvShowsRemoteDataStore
import com.jenny.domain.response.PopularTvShowResponse
import com.jenny.domain.response.SeasonDetailResponse
import com.jenny.domain.response.TvShowDetailResponse
import com.jenny.remote.factory.DataFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class TvShowDataRepositoryImplTest {
    private val dataStoreFactory = Mockito.mock(TvShowsDataStoreFactory::class.java)
    private val remoteDataStore = Mockito.mock(TvShowsRemoteDataStore::class.java)

    private val tvShowDataRepository = TvShowDataRepositoryImpl(dataStoreFactory)


    @Before
    fun setup() {
        stubRemoteDataStore()
    }

    private fun stubRemoteDataStore() {
        Mockito.`when`(dataStoreFactory.tvShowsRemoteDataStore).thenReturn(remoteDataStore)
    }

    private fun stubGetLatestTvShows(single: Single<PopularTvShowResponse>, pageNo: Int) {
        Mockito.`when`(remoteDataStore.getLatestTvShows(pageNo)).thenReturn(single)
    }

    private fun stubGetTvShowDetails(single: Single<TvShowDetailResponse>, tvId: Int) {
        Mockito.`when`(remoteDataStore.getTvShowDetails(tvId)).thenReturn(single)
    }

    private fun stubGetSeasonDetails(single: Single<SeasonDetailResponse>, tvId: Int, seasonNo: Int) {
        Mockito.`when`(remoteDataStore.getSeasonDetails(tvId, seasonNo)).thenReturn(single)
    }

    @Test
    fun getTopRatedMoviesCompletesTest() {
        val pageNo = DataFactory.getRandomInt()
        val popularTvShowsResponse = DataFactory.getPopularTvShows()
        stubGetLatestTvShows(Single.just(popularTvShowsResponse), pageNo)
        val testObserver = tvShowDataRepository.getLatestTvShows(pageNo).test()
        testObserver.assertComplete()
    }

    @Test
    fun getSeasonDetailsCompletesTest() {
        val pageNo = DataFactory.getRandomInt()
        val seasonNo = DataFactory.getRandomInt()
        val seasonDetailResponse = DataFactory.getSeasonDetailResponse()
        stubGetSeasonDetails(Single.just(seasonDetailResponse), pageNo, seasonNo)
        val testObserver = tvShowDataRepository.getSeasonDetails(pageNo, seasonNo).test()
        testObserver.assertComplete()
    }

    @Test
    fun getTvShowDetailsCompletesTest() {
        val pageNo = DataFactory.getRandomInt()
        val showDetails = DataFactory.getShowDetails()
        stubGetTvShowDetails(Single.just(showDetails), pageNo)
        val testObserver = tvShowDataRepository.getTvShowDetails(pageNo).test()
        testObserver.assertComplete()
    }
}