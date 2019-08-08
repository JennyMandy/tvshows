package com.jenny.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jenny.domain.response.PopularTvShowResponse
import com.jenny.domain.usecase.GetTvShows
import com.jenny.presentation.state.Resource
import com.jenny.presentation.state.ResourceState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class GetTvShowsViewModel @Inject
constructor(private val getTvShows: GetTvShows) : ViewModel() {
    private val getTvShowsLiveData = MutableLiveData<Resource<PopularTvShowResponse>>()

    override fun onCleared() {
        getTvShows.disposeAll()
    }

    fun observeGetTvShowsResponse(): LiveData<Resource<PopularTvShowResponse>> {
        return getTvShowsLiveData
    }

    fun getTvShows(pageNo: Int) {
        getTvShowsLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getTvShows.execute(GetTvShowsSubscriber(), GetTvShows.Params.getParams(pageNo))
    }

    private inner class GetTvShowsSubscriber : DisposableSingleObserver<PopularTvShowResponse>() {
        override fun onSuccess(t: PopularTvShowResponse) {
            getTvShowsLiveData.postValue(Resource(ResourceState.SUCCESS, t, null))
        }

        override fun onError(e: Throwable) {
            getTvShowsLiveData.postValue(Resource(ResourceState.ERROR, null, e.message))
        }

    }
}