package com.amvlabs.androidkotlinapps.meeshodemo.adapters.viewholder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.amvlabs.androidkotlinapps.databinding.ItemBaseLayoutBinding
import com.amvlabs.androidkotlinapps.databinding.ProductLayoutBinding
import com.amvlabs.androidkotlinapps.databinding.ViewpagerLayoutBinding
import com.amvlabs.androidkotlinapps.meeshodemo.model.HomeRecyclerViewItems
import kotlin.math.abs

sealed class HomeRecyclerViewHolder(binding: ViewBinding):RecyclerView.ViewHolder(binding.root){
    class BaseLayoutHolder(private val binding: ItemBaseLayoutBinding):HomeRecyclerViewHolder(binding){
         var recyclerView = binding.baseRecyclerView
        var childViewModelAdapter = ChildViewModelAdapter()
        fun bind(items: HomeRecyclerViewItems.LayoutItems) {
            recyclerView.apply {
//                layoutManager = GridLayoutManager(binding.root.context,2,GridLayoutManager.VERTICAL,false)
                layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
                adapter = childViewModelAdapter
            }
            Log.d("TAG", "bind:${items.list.size} ")
            binding.itemTitle.text = items.title
            childViewModelAdapter.itemList = items.list
        }
    }

    class ViewPagerHolder(private val binding: ViewpagerLayoutBinding):HomeRecyclerViewHolder(binding){
        private val childRecyclerAdapter = ChildViewModelAdapter()
        var viewPager = binding.viewpagerRecyclerView
        fun bind(items:HomeRecyclerViewItems.ViewPagerLayoutItem){
            viewPager.apply {
                clipToPadding = false
                clipChildren  = false
                offscreenPageLimit = 3
                getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
                adapter = childRecyclerAdapter
            }
            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(8))
            compositePageTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
                val v = 1 - abs(position)
                page.scaleY = 0.8f  + v * 0.2f
            })
            viewPager.setPageTransformer(compositePageTransformer)
            childRecyclerAdapter.itemList = items.list
        }
    }

    class ProductLayoutHolder(private val binding: ProductLayoutBinding):HomeRecyclerViewHolder(binding){
        var recyclerView = binding.productRecyclerView
        var childViewModelAdapter = ChildViewModelAdapter()
        fun bind(items:HomeRecyclerViewItems.ProductItemsLayout){
            recyclerView.apply {
                layoutManager = GridLayoutManager(binding.root.context,2,GridLayoutManager.VERTICAL,false)
                adapter = childViewModelAdapter
            }
            binding.productLayoutTitle.text = items.title
            childViewModelAdapter.itemList = items.list
        }
    }

}
