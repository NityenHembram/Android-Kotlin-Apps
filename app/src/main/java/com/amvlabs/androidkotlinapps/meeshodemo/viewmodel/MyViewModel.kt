package com.amvlabs.androidkotlinapps.meeshodemo.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amvlabs.androidkotlinapps.meeshodemo.model.*
import com.amvlabs.androidkotlinapps.meeshodemo.utils.ParseJsonFile
import org.json.JSONObject

class MyViewModel(application: Application):AndroidViewModel(application) {
    val ctx = application
    val parseJsonFile = ParseJsonFile()
    val allItems = mutableListOf<HomeRecyclerViewItems>()

    private val _homeListItems = MutableLiveData<List<HomeRecyclerViewItems>>()
    val homeListItems:LiveData<List<HomeRecyclerViewItems>>
        get() = _homeListItems


    fun getProducts(){
        val listOfProductData = mutableListOf<ProductData>()
        val prod = mutableListOf<ChildRecyclerViewItems.Products>()
        val allProductData = parseJsonFile.loadProducts(ctx)
        val jsonObject = JSONObject(allProductData!!)
        val spanSize = jsonObject.get("spansize")
        val title = jsonObject.get("title")
        val productArray = jsonObject.getJSONArray("products")
        for (i in 0 until productArray.length()){
            val name = productArray.getJSONObject(i).get("title")
            val image = productArray.getJSONObject(i).get("image")
            val description = productArray.getJSONObject(i).get("description")
            val price = productArray.getJSONObject(i).get("price")
            val rating = productArray.getJSONObject(i).get("rating")
            val p = ProductData(description.toString(),image.toString(), price.toString() ,rating.toString(),name.toString())
            listOfProductData.add(p)
            prod.add(ChildRecyclerViewItems.Products(listOfProductData,spanSize.toString(),title.toString()))
        }
        allItems.add(HomeRecyclerViewItems.ProductItemsLayout(spanSize.toString(),title.toString(),prod))
        _homeListItems.value = allItems

    }

    fun getCategory(){
        allItems.clear()
        val listOfCategories = mutableListOf<CategoryX>()
        val cate = mutableListOf<ChildRecyclerViewItems>()

        val allCategory = parseJsonFile.loadCategory(ctx)
        val jsonObject = JSONObject(allCategory!!)
        val spanSize = jsonObject.get("spansize")
        val title = jsonObject.get("title")
        val categoryArray = jsonObject.getJSONArray("categories")
        for (i in 0 until categoryArray.length()){
            val image = categoryArray.getJSONObject(i).get("image")
            val title = categoryArray.getJSONObject(i).get("name")
            val c = CategoryX(image.toString(),title.toString())
            listOfCategories.add(c)
            cate.add(ChildRecyclerViewItems.Category(listOfCategories,spanSize.toString(),title.toString()))
        }
        allItems.add(HomeRecyclerViewItems.LayoutItems(spanSize.toString(),title.toString(),cate))
        _homeListItems.value = allItems
    }

    fun getAllOffer(){
        val listOfOffers = mutableListOf<OfferX>()
        val offers = mutableListOf<ChildRecyclerViewItems>()

        val allOffer = parseJsonFile.loadOffer(ctx)
        val jsonObject = JSONObject(allOffer!!)
        val spanSize = jsonObject.get("spansize")
        val title = jsonObject.get("title")
        val jsonArray = jsonObject.getJSONArray("offers")
        for(i in 0 until jsonArray.length()){
            val image = jsonArray.getJSONObject(i).get("image")
            val o = OfferX(image.toString())
            listOfOffers.add(o)
            offers.add(ChildRecyclerViewItems.Offers(listOfOffers,spanSize.toString(),title.toString(),))
        }
        allItems.add(HomeRecyclerViewItems.ViewPagerLayoutItem(spanSize.toString(),title.toString(),offers))
        _homeListItems.value = allItems
    }

    fun getBestSeller(){
        val listOfSeller = mutableListOf<BestSellerX>()
        val seller = mutableListOf<ChildRecyclerViewItems>()

        val allSeller = parseJsonFile.loadBestSeller(ctx)
        val jsonObject = JSONObject(allSeller!!)
        val spanSize = jsonObject.get("spansize")
        val title = jsonObject.get("title")
        val jsonArray = jsonObject.getJSONArray("items")
        for(i in 0 until jsonArray.length()){
            val image = jsonArray.getJSONObject(i).get("image")
            val item_name = jsonArray.getJSONObject(i).get("item name")
            val item_price = jsonArray.getJSONObject(i).get("item price")
            val s = BestSellerX(image.toString(),item_name.toString(),item_price.toString())
            listOfSeller.add(s)
            seller.add(ChildRecyclerViewItems.BestSellers(listOfSeller
                ,spanSize.toString(),title.toString()))
        }
        allItems.add(HomeRecyclerViewItems.LayoutItems(spanSize.toString(),title.toString(),seller))
        _homeListItems.value = allItems
    }



}