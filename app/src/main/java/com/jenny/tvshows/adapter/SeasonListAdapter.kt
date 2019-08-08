package com.jenny.tvshows.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jenny.domain.model.Seasons
import com.jenny.tvshows.Constants
import com.jenny.tvshows.R
import com.jenny.tvshows.activity.ActivitySeasonDetail
import com.squareup.picasso.Picasso

class SeasonListAdapter(private val context: Context, private val tvId: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var inflater: LayoutInflater
    private var mList: List<Seasons>

    init {
        this.inflater = LayoutInflater.from(context)
        mList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SeasonListViewHolder(inflater.inflate(R.layout.item_season, parent, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SeasonListViewHolder) {
            val season = mList.get(position)
            holder.name.text = season.name
            holder.seasonNo.text = season.season_number.toString()
            holder.seasonItemLayout.setOnClickListener {
                val intent = Intent(context, ActivitySeasonDetail::class.java)
                if (tvId > 0) {
                    intent.putExtra(Constants.TV_ID, tvId)
                }
                intent.putExtra(Constants.SEASON_NO, season.season_number)
                context.startActivity(intent)
            }
            Picasso.get().load(Constants.IMAGE_URL + season.poster_path)
                .into(holder.poster)
        }
    }

    fun setItems(list: List<Seasons>) {
        mList = list
    }

    internal inner class SeasonListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        public var name: TextView
        public var seasonNo: TextView
        public var poster: ImageView
        public var seasonItemLayout: ConstraintLayout

        init {
            name = view.findViewById(R.id.name)
            seasonNo = view.findViewById(R.id.season_no)
            poster = view.findViewById(R.id.poster)
            seasonItemLayout = view.findViewById(R.id.season_item_layout)
        }
    }
}