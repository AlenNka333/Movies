package com.hfad.movies.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.hfad.movies.R
import com.hfad.movies.database.FavouriteMovie
import com.hfad.movies.database.MovieViewModel
import com.hfad.movies.json.Movie
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    lateinit var imageView: ImageView
    lateinit var imageStar: ImageView

    lateinit var title: TextView
    lateinit var real_title: TextView
    lateinit var raiting: TextView
    lateinit var release_date: TextView
    lateinit var description: TextView

    lateinit var viewModel: MovieViewModel
    lateinit var movie: Movie

    var favouriteMovie: FavouriteMovie? = null

    var movie_id = 0

    val big_path = "https://image.tmdb.org/t/p/w780"


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
                viewModel.insertFavouriteMovie(FavouriteMovie(movie))
                Toast.makeText(this.context, "Added to favourites", Toast.LENGTH_SHORT).show()

            } else {
                viewModel.deleteFavouriteMovie(favouriteMovie!!)
                Toast.makeText(this.context, "Erased from favourites", Toast.LENGTH_SHORT).show()
            }
            setFavourite()
        }
        setFavourite()
        return root
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

}