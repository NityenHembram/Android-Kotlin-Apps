package com.amvlabs.androidkotlinapps.warecover.adapter

import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.amvlabs.androidkotlinapps.warecover.fragments.ChatFragment
import com.amvlabs.androidkotlinapps.warecover.fragments.StatusFragment


class PagerAdapter(fragmentManager:FragmentManager,lifecycle: Lifecycle,var actionBar: Toolbar):FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
         when(position){
            0 -> {fragment = ChatFragment() }
            1 -> {
            fragment = StatusFragment()
            }
            else -> {fragment}
        }
        return fragment
    }
}