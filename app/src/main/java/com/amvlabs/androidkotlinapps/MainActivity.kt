package com.amvlabs.androidkotlinapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amvlabs.androidkotlinapps.databinding.ActivityMainBinding
import com.amvlabs.androidkotlinapps.emicalculator.MainActivityEmiCalculator
import com.amvlabs.androidkotlinapps.meeshodemo.MainActivityMeesho
import com.amvlabs.androidkotlinapps.messagesender.MainActivityMessageSender
import com.amvlabs.androidkotlinapps.moviesapp.MainActivityMoviesApp
import com.amvlabs.androidkotlinapps.warecover.MainActivityWArecovery

class MainActivity : AppCompatActivity(){

    lateinit var bind : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.meeshoDemo.setOnClickListener {
            startActivity(Intent(this,MainActivityMeesho::class.java))
        }

        bind.messageSender.setOnClickListener{
            startActivity(Intent(this,MainActivityMessageSender::class.java))
        }

        bind.moviesApp.setOnClickListener {
            startActivity(Intent(this, MainActivityMoviesApp::class.java))
        }

        bind.waRecover.setOnClickListener {
            startActivity(Intent(this,MainActivityWArecovery::class.java))
        }

        bind.loanCalculator.setOnClickListener {
            startActivity(Intent(this,MainActivityEmiCalculator::class.java))
        }
    }
}