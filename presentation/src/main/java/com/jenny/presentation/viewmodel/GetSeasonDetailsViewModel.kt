package com.jenny.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jenny.domain.response.SeasonDetailResponse
import com.jenny.domain.usecase.GetSeasonDetails
import com.jenny.presentation.state.Resource
import com.jenny.presentation.state.ResourceState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class GetSeasonDetailsViewModel @Inject
constructor(private val getSeasonDetails: GetSeasonDetails) : ViewModel() {
    private val getSeasonDetailsLiveData = MutableLiveData<Resource<SeasonDetailResponse>>()

    override fun onCleared() {
        getSeasonDetails.disposeAll()
    }

    fun observeGetSeasonDetailsResponse(): LiveData<Resource<SeasonDetailResponse>> {
        return getSeasonDetailsLiveData
    }

    fun getSeasonDetails(pageNo: Int, seasonNo: Int) {
        getSeasonDetailsLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getSeasonDetails.execute(GetSeasonDetailsSubscriber(), GetSeasonDetails.Params.getParams(pageNo, seasonNo))
    }

    private inner class GetSeasonDetailsSubscriber : DisposableSingleObserver<SeasonDetailResponse>() {
        override fun onSuccess(t: SeasonDetailResponse) {
            getSeasonDetailsLiveData.postValue(Resource(ResourceState.SUCCESS, t, null))
        }

        override fun onError(e: Throwable) {
            getSeasonDetailsLiveData.postValue(Resource(ResourceState.ERROR, null, e.message))
        }

    }
}