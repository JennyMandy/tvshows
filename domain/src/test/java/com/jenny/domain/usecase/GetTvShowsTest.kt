package com.jenny.domain.usecase

import com.jenny.domain.repository.TvShowRepository
import com.jenny.domain.response.PopularTvShowResponse
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
class GetTvShowsTest {
    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    private lateinit var getTvShows: GetTvShows

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getTvShows = GetTvShows(tvShowRepository, postExecutionThread)
    }

    private fun stubGetLatestTvShows(single: Single<PopularTvShowResponse>, pageNo: Int) {
        Mockito.`when`(tvShowRepository.getLatestTvShows(pageNo)).thenReturn(single)
    }

    @Test
    fun getSearchedMoviesCompletesTest() {
        val pageNo = DataFactory.getRandomInt()
        val tvShowResponse = DataFactory.getPopularTvShows()
        stubGetLatestTvShows(Single.just(tvShowResponse), pageNo)
        val testObserver = getTvShows.buildUseCaseObservable(GetTvShows.Params.getParams(pageNo)).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun getSearchedMoviesThrowsErrorIfPageNoIsZeroTest() {
        val pageNo = 0
        val tvShowResponse = DataFactory.getPopularTvShows()
        stubGetLatestTvShows(Single.just(tvShowResponse), pageNo)
        getTvShows.buildUseCaseObservable(GetTvShows.Params.getParams(pageNo)).test()
    }

    @Test(expected = IllegalArgumentException::class)
    fun getSearchedMoviesThrowsErrorIfParamsIsNullTest() {
        val pageNo = DataFactory.getRandomInt()
        val tvShowResponse = DataFactory.getPopularTvShows()
        stubGetLatestTvShows(Single.just(tvShowResponse), pageNo)
        getTvShows.buildUseCaseObservable(null).test()
    }

    @Test(expected = IllegalArgumentException::class)
    fun getSearchedMoviesThrowsErrorIfObserverIsNullTest() {
        getTvShows = GetTvShows(tvShowRepository, postExecutionThread)
        getTvShows.execute(null)
    }
}