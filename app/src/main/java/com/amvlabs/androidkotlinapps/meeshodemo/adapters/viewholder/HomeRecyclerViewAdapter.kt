package com.amvlabs.androidkotlinapps.meeshodemo.adapters.viewholder

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.androidkotlinapps.R
import com.amvlabs.androidkotlinapps.databinding.ItemBaseLayoutBinding
import com.amvlabs.androidkotlinapps.databinding.ProductLayoutBinding
import com.amvlabs.androidkotlinapps.databinding.ViewpagerLayoutBinding
import com.amvlabs.androidkotlinapps.meeshodemo.model.HomeRecyclerViewItems

class HomeRecyclerViewAdapter : RecyclerView.Adapter<HomeRecyclerViewHolder>() {

    var items = listOf<HomeRecyclerViewItems>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        return when (viewType) {
            R.layout.item_base_layout -> HomeRecyclerViewHolder.BaseLayoutHolder(
                ItemBaseLayoutBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            R.layout.viewpager_layout -> HomeRecyclerViewHolder.ViewPagerHolder(
                ViewpagerLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            )

            R.layout.product_layout -> HomeRecyclerViewHolder.ProductLayoutHolder(
                ProductLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }
    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        when (holder) {
            is HomeRecyclerViewHolder.BaseLayoutHolder -> holder.bind(items[position] as HomeRecyclerViewItems.LayoutItems)
            is HomeRecyclerViewHolder.ViewPagerHolder -> holder.bind(items[position] as HomeRecyclerViewItems.ViewPagerLayoutItem)
            is HomeRecyclerViewHolder.ProductLayoutHolder -> holder.bind(items[position] as HomeRecyclerViewItems.ProductItemsLayout)
        }
    }
    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
             is HomeRecyclerViewItems.LayoutItems -> R.layout.item_base_layout
            is HomeRecyclerViewItems.ViewPagerLayoutItem -> R.layout.viewpager_layout
            is HomeRecyclerViewItems.ProductItemsLayout -> R.layout.product_layout
        }
    }

}