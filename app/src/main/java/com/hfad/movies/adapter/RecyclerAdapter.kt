package com.hfad.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.movies.R
import com.hfad.movies.model.Movie
import com.hfad.movies.interfaces.OnPosterClickListener
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val onClickListener: OnPosterClickListener):
    RecyclerView.Adapter<RecyclerAdapter.MovieHolder>() {

    var list: ArrayList<Movie> = ArrayList()
    var isLoaded = false
    val small_path = "https://image.tmdb.org/t/p/w185"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MovieHolder(view)
    }

    fun addData(movies: ArrayList<Movie>){
        list = (movies)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.MovieHolder, position: Int) {

        val movie = list[position]

        val url = String.format("%s%s", small_path, movie.poster_path)

        Picasso.get()
            .load(url)
            .into(holder.imageView)

        holder.itemView.setOnClickListener{ onClickListener.onPosterClick(movie) }
    }

    inner class MovieHolder(v: View): RecyclerView.ViewHolder(v){
        val imageView: ImageView = v.findViewById(R.id.imageViewSmallPoster)
    }

}