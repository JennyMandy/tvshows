package com.jenny.tvshows.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jenny.domain.model.Episode
import com.jenny.tvshows.R

class EpisodeListAdapter(
    private val context: Context,
    private val tvId: Int,
    seasonNo: Int
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var inflater: LayoutInflater
    private var mList: List<Episode>

    init {
        this.inflater = LayoutInflater.from(context)
        mList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SeasonListViewHolder(inflater.inflate(R.layout.item_episode, parent, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SeasonListViewHolder) {
            val season = mList.get(position)
            holder.name.text = season.name
            holder.seasonNo.text = season.season_number.toString()
            holder.overview.text = season.overview
        }
    }

    fun setItems(list: List<Episode>) {
        mList = list
    }

    internal inner class SeasonListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        public var name: TextView
        public var seasonNo: TextView
        public var overview: TextView

        init {
            name = view.findViewById(R.id.name)
            seasonNo = view.findViewById(R.id.season_no)
            overview = view.findViewById(R.id.overview)
        }
    }
}