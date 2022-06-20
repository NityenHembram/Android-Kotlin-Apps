package com.amvlabs.androidkotlinapps.warecover

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.androidkotlinapps.databinding.ActivityMessageBinding
import com.amvlabs.androidkotlinapps.warecover.adapter.MessageRecyclerViewAdapter
import com.amvlabs.androidkotlinapps.warecover.room.MessageViewModel
import com.amvlabs.androidkotlinapps.warecover.utils.Constants.USER_NAME_KEY


class MessageActivity : AppCompatActivity() {

    private lateinit var viewModel: MessageViewModel
    private lateinit var binding: ActivityMessageBinding
    private lateinit var recyclerView: RecyclerView
    private var name = ""
    private var chats = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name = intent.getStringExtra(USER_NAME_KEY).toString()
        recyclerView = binding.chatRV



        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        ).get(MessageViewModel::class.java)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.getAllMessage(name).observe(this, Observer {
            it.forEach {
                chats.add(it)
            }
            recyclerView.adapter = MessageRecyclerViewAdapter(chats)
        })
    }
}