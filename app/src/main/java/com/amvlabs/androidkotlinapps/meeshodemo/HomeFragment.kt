package com.amvlabs.androidkotlinapps.meeshodemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.amvlabs.androidkotlinapps.databinding.FragmentHomeBinding
import com.amvlabs.androidkotlinapps.meeshodemo.adapters.viewholder.HomeRecyclerViewAdapter
import com.amvlabs.androidkotlinapps.meeshodemo.viewmodel.MyViewModel


class HomeFragment : Fragment() {

    private lateinit var viewModel: MyViewModel
    lateinit var binding: FragmentHomeBinding
    var homeRecyclerViewAdapter  = HomeRecyclerViewAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application))[MyViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCategory()
        viewModel.getAllOffer()
        viewModel.getBestSeller()
        viewModel.getProducts()

        binding.mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,false)
            adapter = homeRecyclerViewAdapter
        }

        viewModel.homeListItems.observe(requireActivity()){it ->
            Log.d("TAG", "onCreate:$it ")
            homeRecyclerViewAdapter.items = it
        }
    }

}
