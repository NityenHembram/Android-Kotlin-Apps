package com.amvlabs.androidkotlinapps.moviesapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.androidkotlinapps.R
import com.amvlabs.androidkotlinapps.moviesapp.model.Search
import com.bumptech.glide.Glide

class MoviesRecyclerViewAdapter(var list:List<Search>, val context:Context): RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val movieName = itemView.findViewById<TextView>(R.id.movieNameTxt)
        val movieYear = itemView.findViewById<TextView>(R.id.movieYearTxt)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesRecyclerViewAdapter.ViewHolder {
       val v  = LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
        return ViewHolder(v)
    }



    override fun onBindViewHolder(holder: MoviesRecyclerViewAdapter.ViewHolder, position: Int) {
        val item = list[position]

        Glide.with(context).load(item.Poster).into(holder.imageView)
        holder.movieName.text = item.Title
        holder.movieYear.text = item.Year
    }

    override fun getItemCount(): Int {
        Log.d("TAG", "getItemCount: ${list.size}")
       return list.size
    }
}