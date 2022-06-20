package com.amvlabs.androidkotlinapps.warecover.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.androidkotlinapps.R
import com.amvlabs.androidkotlinapps.warecover.MessageActivity
import com.amvlabs.androidkotlinapps.warecover.adapter.OnItemClickListener
import com.amvlabs.androidkotlinapps.warecover.adapter.SenderRecyclerViewAdapter
import com.amvlabs.androidkotlinapps.warecover.room.MessageViewModel
import com.amvlabs.androidkotlinapps.warecover.utils.Constants.USER_NAME_KEY
import com.amvlabs.androidkotlinapps.warecover.utils.StringFilter




private const val TAG = "chat"

class ChatFragment : Fragment(), OnItemClickListener {

    private lateinit var viewModel: MessageViewModel
    private lateinit var recyclerView: RecyclerView
    private var userName = mutableSetOf<String>()
    private var allMessages = mutableListOf<MutableList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[MessageViewModel::class.java]
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.userRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())




        viewModel.allData.observe(viewLifecycleOwner, Observer {
            it.forEach { msg ->
                userName.add(StringFilter.filter(msg.userName))
            }
//            for (i in 0 until userName.size) {
//                Log.d(TAG, "onViewCreated: ${userName.elementAt(i)}")
//                viewModel.getAllMessage(userName.elementAt(i))
//                    .observe(viewLifecycleOwner, Observer { x ->
//                        allMessages.add(i, x as MutableList<String>)
//                    })
//            }
            recyclerView.adapter = SenderRecyclerViewAdapter(userName, allMessages, this)
        })
    }


    override fun itemClicked(position: Int) {
        val intent = Intent(requireActivity(), MessageActivity::class.java)
        intent.putExtra(USER_NAME_KEY, userName.elementAt(position))
        requireActivity().startActivity(intent)
    }

}