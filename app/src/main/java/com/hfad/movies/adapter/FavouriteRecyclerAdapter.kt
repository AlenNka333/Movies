package com.hfad.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.movies.R
import com.hfad.movies.model.FavouriteMovie
import com.squareup.picasso.Picasso

class FavouriteRecyclerAdapter: RecyclerView.Adapter<FavouriteRecyclerAdapter.FavouritesHolder>() {

    var list: ArrayList<FavouriteMovie> = ArrayList()
    val small_path = "https://image.tmdb.org/t/p/w185"

    fun addData(movies: ArrayList<FavouriteMovie>){
        list = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favourite_item_layout, parent, false)
        return FavouritesHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FavouritesHolder, position: Int) {

        val favouriteMovie = list[position]
        val url = String.format("%s%s", small_path, favouriteMovie.poster_path)
        Picasso.get().load(url).into(holder.imageView)

    }

    inner class FavouritesHolder(v: View): RecyclerView.ViewHolder(v){
        val imageView: ImageView = v.findViewById(R.id.imageViewFavourite)
    }

}