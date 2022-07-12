package com.example.nasapictures.view.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nasapictures.view.picture.PictureOfTheDayFragment

class ViewPager2AdapterForEarthFragment(fragmentActivity: Fragment) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = arrayOf(
        PictureOfTheDayFragment.newInstance(2),
        PictureOfTheDayFragment.newInstance(1),
        PictureOfTheDayFragment.newInstance(0)
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}