package com.amvlabs.androidkotlinapps.moviesapp.model

data class Movies(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)