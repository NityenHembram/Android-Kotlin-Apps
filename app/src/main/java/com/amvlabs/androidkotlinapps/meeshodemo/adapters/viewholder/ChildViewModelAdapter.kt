package com.amvlabs.androidkotlinapps.meeshodemo.adapters.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.androidkotlinapps.R
import com.amvlabs.androidkotlinapps.databinding.ItemBestSellerBinding
import com.amvlabs.androidkotlinapps.databinding.ItemCategoriesBinding
import com.amvlabs.androidkotlinapps.databinding.ItemOfferBannerBinding
import com.amvlabs.androidkotlinapps.databinding.ItemProductBinding
import com.amvlabs.androidkotlinapps.meeshodemo.model.ChildRecyclerViewItems

class ChildViewModelAdapter:RecyclerView.Adapter<ChildRecyclerViewHolder>(){
    var itemList = listOf<ChildRecyclerViewItems>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildRecyclerViewHolder {
         return when(viewType){
            R.layout.item_product -> ChildRecyclerViewHolder.ProductsViewHolder(
               ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            )
             R.layout.item_categories -> ChildRecyclerViewHolder.CategoryViewHolder(
                 ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
             )
             R.layout.item_offer_banner -> ChildRecyclerViewHolder.OfferBannerViewHolder(
                 ItemOfferBannerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
             )
             R.layout.item_best_seller -> ChildRecyclerViewHolder.BestSellerViewHolder(
                 ItemBestSellerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
             )
             else -> { throw IllegalArgumentException("Invalid ViewType Provided")}
         }
    }

    override fun onBindViewHolder(holder: ChildRecyclerViewHolder, position: Int) {
        when(holder){
            is ChildRecyclerViewHolder.BestSellerViewHolder -> holder.bind(itemList[position] as ChildRecyclerViewItems.BestSellers,position)
            is ChildRecyclerViewHolder.CategoryViewHolder -> holder.bind(itemList[position] as ChildRecyclerViewItems.Category,position)
            is ChildRecyclerViewHolder.OfferBannerViewHolder -> holder.bind(itemList[position] as ChildRecyclerViewItems.Offers,position)
            is ChildRecyclerViewHolder.ProductsViewHolder -> holder.bind(itemList[position] as ChildRecyclerViewItems.Products,position)
        }
    }

    override fun getItemCount(): Int  = itemList.size

    override fun getItemViewType(position: Int): Int {
        return when(itemList[position]){
             is ChildRecyclerViewItems.BestSellers -> R.layout.item_best_seller
             is ChildRecyclerViewItems.Category -> R.layout.item_categories
             is ChildRecyclerViewItems.Offers -> R.layout.item_offer_banner
             is ChildRecyclerViewItems.Products -> R.layout.item_product
        }
    }
}