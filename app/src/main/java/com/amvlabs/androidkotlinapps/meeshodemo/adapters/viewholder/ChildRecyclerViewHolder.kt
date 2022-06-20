package com.amvlabs.androidkotlinapps.meeshodemo.adapters.viewholder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.amvlabs.androidkotlinapps.databinding.ItemBestSellerBinding
import com.amvlabs.androidkotlinapps.databinding.ItemCategoriesBinding
import com.amvlabs.androidkotlinapps.databinding.ItemOfferBannerBinding
import com.amvlabs.androidkotlinapps.databinding.ItemProductBinding
import com.amvlabs.androidkotlinapps.meeshodemo.model.ChildRecyclerViewItems
import com.amvlabs.androidkotlinapps.meeshodemo.model.ProductData
import com.squareup.picasso.Picasso

sealed class ChildRecyclerViewHolder(binding:ViewBinding):RecyclerView.ViewHolder(binding.root) {
    class CategoryViewHolder(private val binding: ItemCategoriesBinding):ChildRecyclerViewHolder(binding){
        fun bind(category: ChildRecyclerViewItems.Category, position: Int) {
            val list =category.categories[position]
            binding.categoryTxt.text = list.name
            Picasso.get().load(list.image).into(binding.categoryImg)
        }
    }
    class OfferBannerViewHolder(private val binding: ItemOfferBannerBinding):ChildRecyclerViewHolder(binding){
        fun bind(offers: ChildRecyclerViewItems.Offers, position: Int) {
            val list = offers.offers[position]
            Picasso.get().load(list.image).into(binding.bannerImg)
        }
    }
    class BestSellerViewHolder(private val binding: ItemBestSellerBinding):ChildRecyclerViewHolder(binding){
        fun bind(bestSellers: ChildRecyclerViewItems.BestSellers, position: Int) {
            val list  = bestSellers.bestSellerXES[position]
            binding.bstTxt.text = list.name
            binding.bstPrice.text = list.price
            Picasso.get().load(list.image).into(binding.bstImg)
        }
    }
    class ProductsViewHolder(private val binding: ItemProductBinding):ChildRecyclerViewHolder(binding){
        fun bind(products: ChildRecyclerViewItems.Products, position: Int) {
            val list : ProductData = products.productData[position]
            binding.productName.text = list.title
            binding.productPrice.text = list.price
            binding.productRating.text = list.rating
            Picasso.get().load(list.image).into(binding.productImg)
        }
    }
}