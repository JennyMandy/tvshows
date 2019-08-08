package com.jenny.remote.service

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TvShowApiServiceFactory {
    fun getTvShowApiService(): TvShowApiService {
        return makeApiservice(makeOkHttpclient(makeHttpLoggingInterceptor()))
    }

    private fun makeApiservice(okHttpClient: OkHttpClient): TvShowApiService {
        val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(TvShowApiService::class.java)
    }

    private fun makeOkHttpclient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
    }

    private fun makeHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
}