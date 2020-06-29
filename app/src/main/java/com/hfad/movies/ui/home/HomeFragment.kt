package com.hfad.topmovies.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.hfad.movies.R
import com.hfad.movies.adapter.RecyclerAdapter
import com.hfad.movies.database.MovieViewModel
import com.hfad.movies.model.HomeFeed
import com.hfad.movies.model.Movie
import com.hfad.movies.interfaces.OnPosterClickListener
import com.hfad.movies.ui.detail.DetailFragment
import com.hfad.movies.utils.NetworkUtils
import okhttp3.*
import java.io.IOException


class HomeFragment : Fragment(), OnPosterClickListener {

    lateinit var textView1: TextView
    lateinit var textView2: TextView

    lateinit var switch: Switch

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerAdapter


    lateinit var homeViewModel: MovieViewModel

    var flagSort = false


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel = ViewModelProviders.of(this.requireActivity()).get(MovieViewModel::class.java)

        textView1 = root.findViewById(R.id.textView1)
        textView1.setOnClickListener{
            switch.isChecked = false
            homeViewModel.deleteAll()
            flagSort = false
            checkFlag()
            fetchJson()
        }
        textView2 = root.findViewById(R.id.textView4)
        textView2.setOnClickListener {
            switch.isChecked = true
            homeViewModel.deleteAll()
            flagSort = true
            checkFlag()
            fetchJson()
        }

        switch = root.findViewById(R.id.switchSort)
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            flagSort = isChecked
            homeViewModel.deleteAll()
            checkFlag()
            fetchJson()
        }
        recyclerView = root.findViewById(R.id.recyclerViewMain)
        recyclerView.layoutManager = GridLayoutManager(inflater.context, 3)
        adapter = RecyclerAdapter(this)
        recyclerView.adapter = adapter

        getData()

        return root
    }


    fun checkFlag(){
        if(flagSort){
            textView2.setTextColor(resources.getColor(R.color.colotText))
            textView1.setTextColor(resources.getColor(R.color.colorAccent))
        }
        else{
            textView1.setTextColor(resources.getColor(R.color.colotText))
            textView2.setTextColor(resources.getColor(R.color.colorAccent))
        }
    }


    fun fetchJson(){

        val url = NetworkUtils().buildUrl(1, flagSort).toString()

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(layoutInflater.context, "check url", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call, response: Response) {

                val body = response.body?.string()
                val json = GsonBuilder().create().fromJson(body, HomeFeed::class.java)

                val res = json.result
                if(!res.isEmpty()){
                    homeViewModel.deleteAll()
                    for(i in res){
                        homeViewModel.insert(i)
                    }
                }

            }

        })

    }

    fun getData(){
        homeViewModel.getAll().observe(viewLifecycleOwner,
        Observer<List<Movie>>{
            adapter.addData(it as ArrayList<Movie>)
        })
    }


    override fun onPosterClick(movie: Movie) {
        val bundle = Bundle()
        bundle.putInt("id", movie.id)

        val fragment2 = DetailFragment()
        fragment2.setArguments(bundle)

        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
        fragmentTransaction?.replace(R.id.frame, fragment2)?.addToBackStack(null)
        fragmentTransaction?.commit()
    }


}