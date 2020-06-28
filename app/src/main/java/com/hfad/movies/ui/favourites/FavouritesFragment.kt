package com.hfad.topmovies.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.movies.R
import com.hfad.movies.adapter.FavouriteRecyclerAdapter
import com.hfad.movies.adapter.RecyclerAdapter
import com.hfad.movies.database.FavouriteMovie
import com.hfad.movies.database.MovieViewModel

class FavouritesFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FavouriteRecyclerAdapter

    lateinit var viewModel: MovieViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_favourites, container, false)
        viewModel = ViewModelProviders.of(this.requireActivity()).get(MovieViewModel::class.java)

        recyclerView = root.findViewById(R.id.favourites_recycler)
        recyclerView.layoutManager = GridLayoutManager(inflater.context, 3)
        adapter = FavouriteRecyclerAdapter()
        recyclerView.adapter = adapter

        getData()
        return root
    }

    fun getData(){
        viewModel.getFavouriteAll().observe(
            viewLifecycleOwner, Observer<List<FavouriteMovie>>{
                adapter.addData(it as ArrayList<FavouriteMovie>)
            }
        )
    }
}