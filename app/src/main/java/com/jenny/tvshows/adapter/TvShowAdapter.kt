package com.jenny.tvshows.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.jenny.domain.model.TvShows
import com.jenny.tvshows.Constants
import com.jenny.tvshows.R
import com.jenny.tvshows.activity.ActivityTvShowDetail
import com.squareup.picasso.Picasso

class TvShowAdapter(private val context: Context) : BaseAdapter() {
    private var tvShowList: List<TvShows> = arrayListOf()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_tv_show, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val tvShow = tvShowList.get(position)
        viewHolder.name.text = tvShow.name
        Picasso.get().load(Constants.IMAGE_URL + tvShow.poster_path)
            .into(viewHolder.poster)
        viewHolder.poster.setOnClickListener {
            val intent = Intent(context, ActivityTvShowDetail::class.java)
            intent.putExtra(Constants.TV_ID, tvShow.id)
            context.startActivity(intent)
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return tvShowList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return tvShowList.get(position).id.toLong()
    }

    override fun getCount(): Int {
        return tvShowList.size
    }

    fun setList(list: MutableList<TvShows>) {
        tvShowList = list
    }

    private class ViewHolder(view: View) {
        val poster: ImageView
        val name: TextView

        init {
            this.poster = view.findViewById(R.id.poster) as ImageView
            this.name = view.findViewById(R.id.name) as TextView
        }
    }

}