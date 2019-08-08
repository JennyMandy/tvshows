package com.jenny.tvshows.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jenny.domain.model.TvShows
import com.jenny.presentation.state.ResourceState
import com.jenny.presentation.viewmodel.GetTvShowsViewModel
import com.jenny.tvshows.EndlessScrollListener
import com.jenny.tvshows.R
import com.jenny.tvshows.adapter.TvShowAdapter
import com.jenny.tvshows.dependencies.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ActivityTvShows : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModel: GetTvShowsViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var gridView: GridView
    private lateinit var tvShowAdapter: TvShowAdapter
    private var tvShowsList: MutableList<TvShows>? = null

    companion object {
        fun initActivity(context: Context): Intent {
            return Intent(context, ActivityTvShows::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_shows)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GetTvShowsViewModel::class.java)
        gridView = findViewById(R.id.grid_tv_shows)
        tvShowAdapter = TvShowAdapter(this)
        gridView.adapter = tvShowAdapter
        gridView.setOnScrollListener(object :EndlessScrollListener() {
            override fun onLoadMore(page: Int, totalItemsCount: Int): Boolean {
                loadNextPage(page)
                return true
            }

        })

        observeGetTvShowsResponse()
        getTvShows(1)
    }

    private fun observeGetTvShowsResponse() {
        viewModel.observeGetTvShowsResponse().observe(this, Observer {
            when (it?.status) {
                ResourceState.SUCCESS -> {
                    val data = it.data
                    if (data != null) {
                        if (tvShowsList == null) {
                            tvShowsList = data.results
                        } else {
                            tvShowsList?.addAll(data.results)
                        }
                        tvShowsList?.let { tvShowAdapter.setList(it) }
                        tvShowAdapter.notifyDataSetChanged()
                    }
                }
                ResourceState.LOADING -> {

                }
                ResourceState.ERROR -> {

                }
                else -> {
                }
            }
        })
    }

    private fun loadNextPage(pageNo: Int) {
        getTvShows(pageNo)
    }

    private fun getTvShows(pageNo: Int) {
        viewModel.getTvShows(pageNo)
    }
}