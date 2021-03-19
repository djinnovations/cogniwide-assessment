package com.djphy.example.moviesgriddisplay.features.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.djphy.example.moviesgriddisplay.R
import com.djphy.example.moviesgriddisplay.remote.network.models.MoviesList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.viewholder_movie_item.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ItemViewHolder>() {

    private var mDataList = emptyList<MoviesList.MovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_movie_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(mDataList[position])
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: MoviesList.MovieItem?) {
            item?.let {
                itemView.apply {
                    tvTitle.text = it.title
                    Picasso.get()
                        .load("https://image.tmdb.org/t/p/w500/${it.imgUrl}")
                        .into(ivView)
                }
            }
        }
    }

    override fun getItemCount(): Int = mDataList.size

    fun submitList(dataList: List<MoviesList.MovieItem>){
        mDataList = dataList
        notifyDataSetChanged()
    }
}