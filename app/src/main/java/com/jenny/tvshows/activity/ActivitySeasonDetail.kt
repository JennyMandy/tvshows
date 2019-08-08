package com.jenny.tvshows.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jenny.presentation.state.ResourceState
import com.jenny.presentation.viewmodel.GetSeasonDetailsViewModel
import com.jenny.tvshows.Constants
import com.jenny.tvshows.R
import com.jenny.tvshows.adapter.EpisodeListAdapter
import com.jenny.tvshows.dependencies.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ActivitySeasonDetail : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModel: GetSeasonDetailsViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var episodeList: RecyclerView

    private var tvId: Int = 0
    private var seasonNo: Int = 0
    private lateinit var episodeListAdapter: EpisodeListAdapter

    companion object {
        fun initActivity(context: Context): Intent {
            return Intent(context, ActivityTvShows::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_detail)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GetSeasonDetailsViewModel::class.java)

        if (intent != null) {
            tvId = intent.getIntExtra(Constants.TV_ID, 0)
            seasonNo = intent.getIntExtra(Constants.SEASON_NO, 0)
        }

        episodeList = findViewById<RecyclerView>(R.id.episode_list)
        var linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        episodeList.setLayoutManager(linearLayoutManager)
        episodeList.setHasFixedSize(false)
        episodeListAdapter = EpisodeListAdapter(this, tvId, seasonNo)

        observeGetSeasonDetailsResponse()
        getSeasonDetails(tvId, seasonNo)
    }

    private fun observeGetSeasonDetailsResponse() {
        viewModel.observeGetSeasonDetailsResponse().observe(this, Observer {
            when (it?.status) {
                ResourceState.SUCCESS -> {
                    val data = it.data
                    if (data != null) {
                        episodeListAdapter.setItems(data.episodes)
                        episodeList.adapter = episodeListAdapter
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

    private fun getSeasonDetails(tvId: Int, seasonNo: Int) {
        viewModel.getSeasonDetails(tvId, seasonNo)
    }
}
