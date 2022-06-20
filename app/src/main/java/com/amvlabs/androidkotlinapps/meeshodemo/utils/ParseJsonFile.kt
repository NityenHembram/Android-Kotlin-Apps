package com.amvlabs.androidkotlinapps.meeshodemo.utils

import android.content.Context
import android.util.Log
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.CharsetEncoder

class ParseJsonFile {
    fun loadProducts(ctx:Context):String?{
        val a:String = ""
        a.reversed()
        return try {
            val inputStream = ctx.assets.open("products.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            String(buffer, Charset.forName("UTF-8"))
        }catch (e:IOException){
            Log.d("TAG", "loadFromAssets: $e")
            null
        }
    }

    fun loadCategory(ctx:Context):String?{
        return try {
            val inputStream = ctx.assets.open("categories.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            String(buffer, Charset.forName("UTF-8"))
        }catch (e:IOException){
            Log.d("TAG", "loadFromAssets: $e")
            null
        }
    }

    fun loadBestSeller(ctx:Context):String?{
        return try {
            val inputStream = ctx.assets.open("bestseller.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            String(buffer, Charset.forName("UTF-8"))
        }catch (e:IOException){
            Log.d("TAG", "loadFromAssets: $e")
            null
        }
    }

    fun loadOffer(ctx:Context):String?{
        return try {
            val inputStream = ctx.assets.open("offer.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            String(buffer, Charset.forName("UTF-8"))
        }catch (e:IOException){
            Log.d("TAG", "loadFromAssets: $e")
            null
        }
    }
}