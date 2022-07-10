package com.example.nasapictures.view.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager2AdapterForEarthFragment(fragmentActivity: Fragment) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = arrayOf(MarsFragment(), SystemFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}