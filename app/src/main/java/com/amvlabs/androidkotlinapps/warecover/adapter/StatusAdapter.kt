package com.amvlabs.androidkotlinapps.warecover.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.androidkotlinapps.R

import com.squareup.picasso.Picasso

class StatusAdapter(private val list:ArrayList<Uri>,val onImageItemClickListener: OnImageItemClickListener,var ctx:Context):RecyclerView.Adapter<StatusAdapter.ViewHolder>() {
    class ViewHolder(itemView:View,val onImageItemClickListener: OnImageItemClickListener,private val list:ArrayList<Uri>,var ctx:Context):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val imageThumb = itemView.findViewById<ImageView>(R.id.ivThumbnail)
        val save = itemView.findViewById<ImageButton>(R.id.save)
        val share = itemView.findViewById<ImageButton>(R.id.share)
        init {
            save.setOnClickListener(this)
            share.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            when(p0?.id){
                R.id.save ->onImageItemClickListener.onDownButtonClicked(list,adapterPosition,ctx)
                R.id.share ->  onImageItemClickListener.onShareButtonClicked(list,adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.status_items,parent,false)
        return ViewHolder(v,onImageItemClickListener,list,ctx)
    }

    override fun onBindViewHolder(holder: StatusAdapter.ViewHolder, position: Int) {
        val image = list[position]
        Picasso.get().load(image).into(holder.imageThumb)
    }

    override fun getItemCount(): Int {
       return list.size
    }
}
interface OnImageItemClickListener{
//    fun onImageClicked()
    fun onDownButtonClicked(list: ArrayList<Uri>, adapterPosition: Int, ctx: Context)
    fun onShareButtonClicked(list: ArrayList<Uri>, adapterPosition: Int)
}