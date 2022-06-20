package com.amvlabs.androidkotlinapps.meeshodemo

import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.amvlabs.androidkotlinapps.databinding.FragmentCategoryBinding

//import com.amvlabs.meeshodemo.databinding.FragmentCategoryBinding
//import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*


private const val TAG = "catag"

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding

    lateinit var hr: TextView
    lateinit var min: TextView
    lateinit var sec: TextView

//    lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        hr = binding.hr
        min = binding.min
        sec = binding.sec
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        firebaseDatabase = FirebaseDatabase.getInstance()

//        firebaseDatabase.reference.child("offer time").get().addOnSuccessListener {
//            val sdf = SimpleDateFormat("HH:mm:ss")
//            val currentTime: Date ?= null
//            val hour:Float = 0F
//            val minu:Float = 0F
//            val seco:Float = 0F
//            val endTime: Date = sdf.parse(sdf.format(Date(it.value.toString().toLong()))) as Date
//
//            CoroutineScope(Dispatchers.Default).launch {
//                while (it.exists()) {
//                    val currentTime: Date = sdf.parse(sdf.format(Calendar.getInstance().time.time)) as Date
//                    Log.d(TAG, "onViewCreated: ${endTime.time} ${currentTime.time}")
//                    if(endTime != currentTime && endTime > currentTime){
//
//                        val remainTime = (endTime.time - currentTime.time)/1000
//
//                        val hour:Float = remainTime/(60 * 60).toFloat()
//                        val minu:Float = converter(hour) * 60
//                        val seco = (converter(minu) * 60) + 0.1
//                        CoroutineScope(Dispatchers.Main).launch {
//                            setViews(hour.toInt(),minu.toInt(),seco.toInt())
//                        }
//                        delay(1000)
//                    }else{
//                        CoroutineScope(Dispatchers.Main).launch {
//                            binding.timer.visibility = View.GONE
//                            binding.msg.visibility = View.VISIBLE
//                        }
//
//                        if(this.isActive){
//                            this.cancel()
//                            break
//                        }
//                    }
//                }
//            }
//        }.addOnFailureListener {
//            Log.d(TAG, "fail: $it ")
        }

    }

    @SuppressLint("SetTextI18n")
//    private fun setViews(hour: Int, minu: Int, seco: Int) {
//        Log.d(TAG, "setViews: ${hr.toString().length}")
//        if(hour.toString().length > 1 ){
//            hr.text = hour.toString()
//        }else{
//            hr.text = "0${hour.toString()}"
//        }
//        if(minu.toString().length > 1 ){
//            min.text = minu.toString()
//        }else{
//            min.text = "0${minu.toString()}"
//        }
//        if(seco.toString().length > 1){
//            sec.text = seco.toString()
//        }else{
//            sec.text = "0${seco.toString()}"
//        }
//    }
    private fun converter (s :Float):Float{
        val a = s.toString().substringAfter(".")
        val g = StringBuilder()
        g.append(".").append(a)
        return  g.toString().toFloat()
    }


//}


