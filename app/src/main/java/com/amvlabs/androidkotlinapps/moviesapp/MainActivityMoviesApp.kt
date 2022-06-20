package com.amvlabs.androidkotlinapps.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.amvlabs.androidkotlinapps.R
import com.amvlabs.androidkotlinapps.moviesapp.adapter.MoviesRecyclerViewAdapter
import com.amvlabs.androidkotlinapps.moviesapp.model.Search
import com.amvlabs.androidkotlinapps.moviesapp.presenter.MainPresenter
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

 const val TAG = "tag"

class MainActivityMoviesApp : AppCompatActivity(), MainPresenter.View {

    private lateinit var adapter: MoviesRecyclerViewAdapter

    private var presenter = MainPresenter()
    private var userList :MutableList<Search>? = ArrayList()

    @BindView(R.id.movieToolbar)
     lateinit var toolbar: Toolbar

    @BindView(R.id.movieRV)
     lateinit var recyclerView:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_movie)



        ButterKnife.bind(this)
        recyclerView = findViewById(R.id.movieRV)
        toolbar = findViewById(R.id.movieToolbar)
        presenter.attachView(this)
        presenter.getMovieList("Avengers")

        val action = setSupportActionBar(toolbar)


        setUpRecyclerView()
    }

   private fun setUpRecyclerView(){
       recyclerView.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
       adapter = MoviesRecyclerViewAdapter(userList ?: emptyList(),this)

       recyclerView.adapter = adapter
       recyclerView.setHasFixedSize(true)
   }

    override fun notifyDataChange(movieList: List<Search>) {
        adapter.list = movieList
        adapter.notifyDataSetChanged()
    }

    override fun showError() {
       Toast.makeText(this,"No Movie Found",Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_btn,menu)
        val item = menu?.findItem(R.id.search_btn)

        val searchView:SearchView = item?.actionView as SearchView
        searchView.queryHint = "Search"

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                if(!query.isNullOrEmpty()){
                    presenter.getMovieList(query.toString())
                }


                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("TAG", "onQueryTextchange: $newText")
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }
}