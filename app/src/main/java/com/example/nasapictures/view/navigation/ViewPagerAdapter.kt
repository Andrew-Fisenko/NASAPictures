package com.example.nasapictures.view.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.nasapictures.view.recycler.NotesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val fragments =
        arrayOf(EarthFragment(), MarsFragment(), SystemFragment(), NotesFragment())

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> ("Earth")
            1 -> ("Mars")
            2 -> ("Solar system")
            3 -> ("Notes")
            else -> {
                "Error"
            }
        }
    }
}