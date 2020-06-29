package com.hfad.movies.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.hfad.movies.R
import com.hfad.movies.adapter.TrailerRecyclerAdapter
import com.hfad.movies.model.FavouriteMovie
import com.hfad.movies.database.MovieViewModel
import com.hfad.movies.model.Movie
import com.hfad.movies.model.Trailer
import com.hfad.movies.model.TrailerFeed
import com.hfad.movies.interfaces.OnImageTrailerClickListener
import com.hfad.movies.utils.NetworkUtils
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException

class DetailFragment : Fragment(), OnImageTrailerClickListener {

    lateinit var imageView: ImageView
    lateinit var imageStar: ImageView

    lateinit var title: TextView
    lateinit var real_title: TextView
    lateinit var raiting: TextView
    lateinit var release_date: TextView
    lateinit var description: TextView

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TrailerRecyclerAdapter

    lateinit var viewModel: MovieViewModel
    lateinit var movie: Movie

    var favouriteMovie: FavouriteMovie? = null

    var movie_id = 0

    val big_path = "https://image.tmdb.org/t/p/w780"
    val YOUTUBE_URL = "https://www.youtube.com/watch?v="


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_detail, container, false)

        val bundle = this.arguments

        if (bundle != null) {
            movie_id = bundle.getInt("id")
        }

        imageView = root.findViewById(R.id.bigPoster)
        imageStar = root.findViewById(R.id.star)

        title = root.findViewById(R.id.name1)
        real_title = root.findViewById(R.id.real_name1)
        raiting = root.findViewById(R.id.rating1)
        release_date = root.findViewById(R.id.release_date1)
        description = root.findViewById(R.id.description1)

        recyclerView = root.findViewById(R.id.recylerViewDetail)
        recyclerView.layoutManager = LinearLayoutManager(inflater.context)
        adapter = TrailerRecyclerAdapter(this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movie = viewModel.getMovie(movie_id)
        val url = String.format("%s%s", big_path, movie.poster_path)

        Picasso.get()
            .load(url)
            .into(imageView)

        title.text = movie.title
        real_title.text = movie.original_title
        raiting.text = movie.popularity
        release_date.text = movie.release_date
        description.text = movie.overview

        imageStar.setOnClickListener {
            if (favouriteMovie == null) {
                viewModel.insertFavouriteMovie(
                    FavouriteMovie(
                        movie
                    )
                )
                Toast.makeText(this.context, "Added to favourites", Toast.LENGTH_SHORT).show()

            } else {
                viewModel.deleteFavouriteMovie(favouriteMovie!!)
                Toast.makeText(this.context, "Erased from favourites", Toast.LENGTH_SHORT).show()
            }
            setFavourite()
        }
        setFavourite()
        getTrailers()
        return root
    }

    fun getTrailers(){
        val url = NetworkUtils().buildTrailerUrl(movie_id.toString())

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(layoutInflater.context, "check url", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val json = GsonBuilder().create().fromJson(body, TrailerFeed::class.java)
                val res = json.result

                if(res!=null) {
                    activity?.runOnUiThread {
                        adapter.setData(res as ArrayList<Trailer>)
                    }
                }

            }

        })
    }


    fun setFavourite(){
        favouriteMovie = viewModel.getFavouriteMovie(movie_id)
        if(favouriteMovie == null){
            imageStar.setImageResource(R.drawable.empty_star)
        }
        else{
            imageStar.setImageResource(R.drawable.star)
        }
    }

    override fun onImageTrailerClick(key: String) {
        val path = YOUTUBE_URL+key
        val intentTrailer = Intent(Intent.ACTION_VIEW, Uri.parse(path))
        startActivity(intentTrailer)
    }

}