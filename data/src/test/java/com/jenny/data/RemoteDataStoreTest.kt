package com.jenny.data

import com.jenny.data.repository.TvShowRemote
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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RemoteDataStoreTest {
    @Mock
    private lateinit var tvShowRemote: TvShowRemote

    private lateinit var tvShowsRemoteDataStore: TvShowsRemoteDataStore

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        tvShowsRemoteDataStore = TvShowsRemoteDataStore(tvShowRemote)
    }
    
    private fun stubGetLatestTvShows(single: Single<PopularTvShowResponse>, pageNo: Int) {
        Mockito.`when`(tvShowRemote.getLatestTvShows(pageNo)).thenReturn(single)
    }

    private fun stubGetTvShowDetails(single: Single<TvShowDetailResponse>, tvId: Int) {
        Mockito.`when`(tvShowRemote.getTvShowDetails(tvId)).thenReturn(single)
    }

    private fun stubGetSeasonDetails(single: Single<SeasonDetailResponse>, tvId: Int, seasonNo: Int) {
        Mockito.`when`(tvShowRemote.getSeasonDetails(tvId, seasonNo)).thenReturn(single)
    }

    @Test
    fun getTopRatedMoviesCompletesTest() {
        val pageNo = DataFactory.getRandomInt()
        val popularTvShowsResponse = DataFactory.getPopularTvShows()
        stubGetLatestTvShows(Single.just(popularTvShowsResponse), pageNo)
        val testObserver = tvShowsRemoteDataStore.getLatestTvShows(pageNo).test()
        testObserver.assertComplete()
    }

    @Test
    fun getSeasonDetailsCompletesTest() {
        val pageNo = DataFactory.getRandomInt()
        val seasonNo = DataFactory.getRandomInt()
        val seasonDetailResponse = DataFactory.getSeasonDetailResponse()
        stubGetSeasonDetails(Single.just(seasonDetailResponse), pageNo, seasonNo)
        val testObserver = tvShowsRemoteDataStore.getSeasonDetails(pageNo, seasonNo).test()
        testObserver.assertComplete()
    }

    @Test
    fun getTvShowDetailsCompletesTest() {
        val pageNo = DataFactory.getRandomInt()
        val showDetails = DataFactory.getShowDetails()
        stubGetTvShowDetails(Single.just(showDetails), pageNo)
        val testObserver = tvShowsRemoteDataStore.getTvShowDetails(pageNo).test()
        testObserver.assertComplete()
    }
}