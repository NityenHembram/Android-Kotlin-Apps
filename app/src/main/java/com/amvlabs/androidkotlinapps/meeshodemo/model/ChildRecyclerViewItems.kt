package com.amvlabs.androidkotlinapps.meeshodemo.model

sealed class ChildRecyclerViewItems {
    data class Category(
        val categories: List<CategoryX>,
        val spansize: String,
        val title: String
    ) : ChildRecyclerViewItems()

    data class BestSellers(
        val bestSellerXES: List<BestSellerX>,
        val spansize: String,
        val title: String
    ):ChildRecyclerViewItems()

    data class Offers(
        val offers: List<OfferX>,
        val spansize: String,
        val title: String
    ):ChildRecyclerViewItems()

    data class Products(
        val productData: List<ProductData>,
        val spansize: String,
        val title: String
    ) : ChildRecyclerViewItems()

}