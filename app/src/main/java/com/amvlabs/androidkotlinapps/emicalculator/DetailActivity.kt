package com.amvlabs.androidkotlinapps.emicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.androidkotlinapps.R
import com.amvlabs.androidkotlinapps.emicalculator.atapter.RecyclerViewAdapter
import kotlinx.coroutines.*

class DetailActivity : AppCompatActivity() {

    private lateinit var recyclerView:RecyclerView
    private var allData = ArrayList<Transection>()

    private var total:Double = 0.0
    private var monthlyIntrest:Double = 0.0
    private var monthOpen = 0.0
    private var monthClose = 0.0
    private var monthpricipal = 0.0
    private var temp = 0.0


    private var interest:Double = 0.0
    private var principal1:Double = 0.0
    private var opening:Double = 0.0
    private var emi  = 0
    private var t = 0
    private var closing = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val p = intent.extras?.getInt("principal")
        val n = intent.extras?.getDouble("time")
        val r = intent.extras?.getDouble("rate")
        Log.d(TAG, "onCreate: $p  $n  $r)}")



        loadData(p, n , r)




    }

    private fun loadData(p:Int?,n:Double?,r:Double?){
        if(p != null && n != null &&r!=null){
            val emi = getEmi(p,n,r).toInt()

            val totalAmount = (emi * (n*12)).toInt()
            val principal = p * 100000
            val interest = (totalAmount - principal).toInt()
         upload(emi,principal,r,n)
        }
    }

    private  fun upload(e: Int, p: Int, r: Double, time: Double){
        var datas = ArrayList<Transection>()

        Log.d(TAG, "upload: ")
        CoroutineScope(Dispatchers.IO).launch {
            principal1 = p.toDouble()
            monthOpen = p.toDouble()
            emi = e * 12
            t = time.toInt()
            opening = p.toDouble()
            var i = 1

            interest = (r/100)
            monthlyIntrest = (r/100)/12





            while(opening > 0){


                for(i in 1..12){
                    temp =monthOpen * monthlyIntrest
                    total += temp
                    monthpricipal = e - temp
                    monthClose = monthOpen - monthpricipal
                    monthOpen = monthClose
                }
                Log.d(TAG, "monthint:   $total   $monthClose")


//                val v =  opening * interest
                principal1 = emi - total

                closing = opening - principal1
                if(closing < 0 && total < 0){
                    closing = 0.0
                    total = 0.0
                }
                datas.add(Transection(opening.toInt().toString(),emi.toInt().toString(),total.toInt().toString(), principal1.toInt().toString(), closing.toInt().toString()))
                opening = closing
                total = 0.0
                i++
            }
            launch(Dispatchers.Main) {
                recyclerView.adapter = RecyclerViewAdapter(datas)

            }
        }












    }

    private fun getEmi(p: Int, n: Double, r: Double):Double {
        //P x R x (1+R)^N / [(1+R)^N-1] where-
        val pp = p*100000
        var emi:Double = 0.0
        val t = n*12
        emi = if(r != 0.0){
            val R = (r/12)/100
            (pp*R*Math.pow(1+R,t)) /  ((Math.pow(1+R,t)-1))
        }else{
            val v = n/12
            p / t
        }
        return emi
    }
}