package com.jenny.tvshows.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jenny.domain.model.Episode
import com.jenny.tvshows.Constants
import com.jenny.tvshows.R
import com.squareup.picasso.Picasso

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
            val episode = mList.get(position)
            holder.name.text = episode.name
            holder.seasonNo.text = episode.season_number.toString()
            if (!TextUtils.isEmpty(episode.overview)) {
                holder.overview.visibility = View.VISIBLE
                holder.label3.visibility = View.VISIBLE
                holder.overview.text = episode.overview
            } else {
                holder.overview.visibility = View.GONE
                holder.label3.visibility = View.GONE
            }
            Picasso.get().load(Constants.IMAGE_URL + episode.still_path)
                .into(holder.poster)
        }
    }

    fun setItems(list: List<Episode>) {
        mList = list
    }

    internal inner class SeasonListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        public var name: TextView
        public var seasonNo: TextView
        public var overview: TextView
        public var label3: TextView
        public var poster: ImageView

        init {
            name = view.findViewById(R.id.name)
            seasonNo = view.findViewById(R.id.season_no)
            overview = view.findViewById(R.id.overview)
            label3 = view.findViewById(R.id.label3)
            poster = view.findViewById(R.id.poster)
        }
    }
}