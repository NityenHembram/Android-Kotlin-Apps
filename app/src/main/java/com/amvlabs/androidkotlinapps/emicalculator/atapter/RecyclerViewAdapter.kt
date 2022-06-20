package com.amvlabs.androidkotlinapps.emicalculator.atapter

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.androidkotlinapps.R
import com.amvlabs.androidkotlinapps.emicalculator.Transection


class RecyclerViewAdapter(val list:ArrayList<Transection>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val year = itemView.findViewById<TextView>(R.id.years)
        val openingBalance = itemView.findViewById<TextView>(R.id.opening_txt)
        val emi_txt = itemView.findViewById<TextView>(R.id.emi_txt)
        val interestPaid = itemView.findViewById<TextView>(R.id.int_yr_txt)
        val principalPaid = itemView.findViewById<TextView>(R.id.pri_yr_txt)
        val closingBalance: TextView = itemView.findViewById<TextView>(R.id.closing_txt)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.year.text = (position + 1).toString()
        holder.openingBalance.text = item.openingBalance
        holder.emi_txt.text = item.emi
        holder.interestPaid.text = item.IntPaidYearly
        holder.principalPaid.text = item.principalPainYearly
        holder.closingBalance.text = item.closingBalance
    }

    override fun getItemCount(): Int {
        return list.size;
    }
}