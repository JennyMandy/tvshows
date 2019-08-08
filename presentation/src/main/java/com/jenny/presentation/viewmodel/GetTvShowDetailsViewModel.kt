package com.jenny.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jenny.domain.response.TvShowDetailResponse
import com.jenny.domain.usecase.GetTvShowsDetails
import com.jenny.presentation.state.Resource
import com.jenny.presentation.state.ResourceState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class GetTvShowDetailsViewModel @Inject
constructor(private val getTvShowsDetails: GetTvShowsDetails) : ViewModel() {
    private val getTvShowsDetailsLiveData = MutableLiveData<Resource<TvShowDetailResponse>>()

    override fun onCleared() {
        getTvShowsDetails.disposeAll()
    }

    fun observeGetTvShowsDetailsResponse(): LiveData<Resource<TvShowDetailResponse>> {
        return getTvShowsDetailsLiveData
    }

    fun getTvShowsDetails(pageNo: Int) {
        getTvShowsDetailsLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getTvShowsDetails.execute(GetTvShowsDetailsSubscriber(), GetTvShowsDetails.Params.getParams(pageNo))
    }

    private inner class GetTvShowsDetailsSubscriber : DisposableSingleObserver<TvShowDetailResponse>() {
        override fun onSuccess(t: TvShowDetailResponse) {
            getTvShowsDetailsLiveData.postValue(Resource(ResourceState.SUCCESS, t, null))
        }

        override fun onError(e: Throwable) {
            getTvShowsDetailsLiveData.postValue(Resource(ResourceState.ERROR, null, e.message))
        }

    }
}