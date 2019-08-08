package com.jenny.tvshows.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jenny.presentation.state.ResourceState
import com.jenny.presentation.viewmodel.GetTvShowDetailsViewModel
import com.jenny.tvshows.Constants
import com.jenny.tvshows.R
import com.jenny.tvshows.adapter.SeasonListAdapter
import com.jenny.tvshows.dependencies.ViewModelFactory
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ActivityTvShowDetail : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: GetTvShowDetailsViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var poster: ImageView
    private lateinit var name: TextView
    private lateinit var description: TextView
    private lateinit var seasonDetails: TextView
    private lateinit var seasonList: RecyclerView

    private var tvId: Int = 0
    private var seasonNo: Int = 0
    private lateinit var seasonListAdapter: SeasonListAdapter

    companion object {
        fun initActivity(context: Context): Intent {
            return Intent(context, ActivityTvShows::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GetTvShowDetailsViewModel::class.java)
        poster = findViewById(R.id.poster) as ImageView
        name = findViewById(R.id.name) as TextView
        description = findViewById(R.id.description) as TextView
        seasonDetails = findViewById(R.id.season_details) as TextView
        seasonList = findViewById<RecyclerView>(R.id.season_list)
        var linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        seasonList.setLayoutManager(linearLayoutManager)
        seasonList.setHasFixedSize(false)

        if (intent != null) {
            tvId = intent.getIntExtra(Constants.TV_ID, 0)
            seasonNo = intent.getIntExtra(Constants.SEASON_NO, 0)
        }
        seasonListAdapter = SeasonListAdapter(this, tvId)

        observeGetTvShowsDetailsResponse()
        getTvShowsDetails(tvId)
    }

    private fun observeGetTvShowsDetailsResponse() {
        viewModel.observeGetTvShowsDetailsResponse().observe(this, Observer {
            when (it?.status) {
                ResourceState.SUCCESS -> {
                    val data = it.data
                    if (data != null) {
                        name.text = data.name
                        description.text = data.overview
                        seasonDetails.text = data.number_of_seasons.toString()
                        Picasso.get().load(Constants.IMAGE_URL + data?.backdrop_path)
                            .into(poster)
                        seasonListAdapter.setItems(data.seasons)
                        seasonList.adapter = seasonListAdapter
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

    private fun getTvShowsDetails(tvId: Int) {
        viewModel.getTvShowsDetails(tvId)
    }
}
