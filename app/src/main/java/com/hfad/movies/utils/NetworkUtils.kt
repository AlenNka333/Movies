package com.hfad.movies.utils

import android.net.Uri
import java.net.URL
import java.sql.ParameterMetaData
import java.util.*

class NetworkUtils {

    private val url = "https://api.themoviedb.org/3/discover/movie"

    private val trailer_url = "https://api.themoviedb.org/3/movie/%s/videos"

    private val PARAM_ID = "movie_id"
    private val PARAM_VOTE_FILTER = "vote_count.gte"
    private val PARAM_API = "api_key"
    private val PARAM_LANGUAGE = "language"
    private val PARAM_SORT = "sort_by"
    private val PARAM_PAGE = "page"

    private val API_KEY = "63a2548cc3f31e38c97a66fe93015e45"
    private val LANGUAGE = "en-US"
    private val SORT_POPULAR = "popularity.desc"
    private val SORT_TOP = "vote_average.desc"
    private var PAGE = ""
    private var MOVIE_ID = ""

    fun buildTrailerUrl(movie_id: String): URL{
        MOVIE_ID = movie_id
        val uri = Uri.parse(trailer_url).buildUpon()
            .appendQueryParameter(PARAM_API, API_KEY)
            .appendQueryParameter(PARAM_LANGUAGE, LANGUAGE)
            .build()
        val prev_url = String.format(uri.toString(), MOVIE_ID)
            return URL(prev_url)
    }


    fun buildUrl(page: Int, flag: Boolean): URL{
        var sort: String = ""
        if (flag) {
            sort = SORT_TOP

        }
        else sort = SORT_POPULAR
        PAGE = page.toString()

        val uri = Uri.parse(url).buildUpon()
            .appendQueryParameter(PARAM_API, API_KEY)
            .appendQueryParameter(PARAM_LANGUAGE, LANGUAGE)
            .appendQueryParameter(PARAM_SORT, sort)
            .appendQueryParameter(PARAM_PAGE, PAGE)
            .appendQueryParameter(PARAM_VOTE_FILTER, "500")
            .build()
        return URL(uri.toString())
    }
}