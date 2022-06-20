package com.amvlabs.androidkotlinapps.warecover.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.androidkotlinapps.R
import com.amvlabs.androidkotlinapps.warecover.utils.CurrentDateAndTime
import org.w3c.dom.Text
import kotlin.random.Random

private const val TAG = "cyc"
class SenderRecyclerViewAdapter(val alldata: Set<String>, val allMsg:List<List<String>>, val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<SenderRecyclerViewAdapter.ViewHolder>() {
    private var currentUser = ""
    private val color = arrayListOf<Int>(Color.WHITE,Color.RED,Color.BLUE,Color.CYAN,Color.DKGRAY,Color.GRAY,Color.GREEN,Color.MAGENTA,Color.LTGRAY,Color.TRANSPARENT)

    val currentDateAndTime = CurrentDateAndTime()

    class ViewHolder(itemView: View,val onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        var userName = itemView.findViewById<TextView>(R.id.chat_name_txt)
        var userImage = itemView.findViewById<TextView>(R.id.firstLetter)
        val userImageBc = itemView.findViewById<CardView>(R.id.icon)
        val lastChat = itemView.findViewById<TextView>(R.id.lastChat)
        override fun onClick(p0: View?) {
            onItemClickListener.itemClicked(adapterPosition)
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SenderRecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_name_view, parent, false)
        return ViewHolder(v,onItemClickListener)
    }

    override fun onBindViewHolder(holder: SenderRecyclerViewAdapter.ViewHolder, position: Int) {
        val user = alldata.elementAt(position)
//        val msg = allMsg[position]
//        Log.d(TAG, "onBindViewHolder: ${msg.get(msg.size-1)}")

        holder.userImage.text = firstLetter(user)
        holder.userName.text =  user
//        holder.lastChat.text = msg[msg.size-1]
        holder.userImageBc.setCardBackgroundColor(randColor())
    }
    fun randColor():Int{
        val rand = Random.nextInt(0,color.size-1)
        return color[rand]
    }
    fun firstLetter(user: String):String {
        val firstLetter = user.elementAt(0)
        return firstLetter.toString()
    }

    override fun getItemCount(): Int {
        return alldata.size
    }
}
interface OnItemClickListener{
    fun itemClicked(position:Int)
}