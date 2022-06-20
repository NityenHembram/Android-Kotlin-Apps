package com.amvlabs.androidkotlinapps.warecover.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.androidkotlinapps.R

class MessageRecyclerViewAdapter(val chats:List<String>):RecyclerView.Adapter<MessageRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val chatTxt = itemView.findViewById<TextView>(R.id.msg_txt)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageRecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.message_item,parent,false)
        return ViewHolder(v)
    }



    override fun onBindViewHolder(holder: MessageRecyclerViewAdapter.ViewHolder, position: Int) {
        val c = chats[position]
        holder.chatTxt.text = c
    }

    override fun getItemCount(): Int {
       return chats.size
    }
}