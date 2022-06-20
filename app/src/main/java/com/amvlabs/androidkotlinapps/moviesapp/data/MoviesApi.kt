package com.amvlabs.androidkotlinapps.moviesapp.data

import com.amvlabs.androidkotlinapps.moviesapp.model.Movies
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://movie-database-alternative.p.rapidapi.com/?s=Avengers%20Endgame&r=json&page=1/&rapidapi-key=893cc12f89mshfee0fffbfe9df46p1323f8jsn6195fc90ba2e
private const val API_KEY = "893cc12f89mshfee0fffbfe9df46p1323f8jsn6195fc90ba2e"
private val URL = "https://movie-database-alternative.p.rapidapi.com/"

interface MoviesApi {

    @GET("?r=json&page=1/&rapidapi-key=$API_KEY")
    fun getMovies(@Query("s")name:String): Observable<Movies>

    companion object {
        fun create(): MoviesApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(MoviesApi::class.java)
        }
    }

}
