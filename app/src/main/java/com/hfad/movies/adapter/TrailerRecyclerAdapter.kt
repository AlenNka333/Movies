package com.hfad.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.movies.R
import com.hfad.movies.model.Trailer
import com.hfad.movies.interfaces.OnImageTrailerClickListener

class TrailerRecyclerAdapter(private val onImageTrailerClickListener: OnImageTrailerClickListener): RecyclerView.Adapter<TrailerRecyclerAdapter.TrailerHolder>() {

    private var list: ArrayList<Trailer> = ArrayList()

    inner class TrailerHolder(v: View): RecyclerView.ViewHolder(v) {
        val textViewTrailer: TextView = v.findViewById(R.id.textViewTrailer)
        val imageViewTrailer: ImageView = v.findViewById(R.id.imageViewTrailer)
    }

    fun setData(trailers: ArrayList<Trailer>){
        list = trailers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_detail_layout, parent, false)
        return TrailerHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TrailerHolder, position: Int) {
        holder.textViewTrailer.text = list[position].name
        holder.imageViewTrailer.setOnClickListener{onImageTrailerClickListener.onImageTrailerClick(list[position].key)}
    }
}