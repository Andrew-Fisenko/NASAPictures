package com.example.nasapictures.view.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nasapictures.R
import com.example.nasapictures.databinding.FragmentEarthBinding
import com.example.nasapictures.view.picture.PictureOfTheDayFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class EarthFragment : Fragment() {

    private var _binding: FragmentEarthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = ViewPager2AdapterForEarthFragment(this)
        bindTabLayout()


    }

    private fun bindTabLayout() {
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = when (position) {
                        0 -> {resources.getString(R.string.two_days_ago)}
                        1 -> {resources.getString(R.string.yesterday)}
                        2 -> {resources.getString(R.string.today)}
                        else -> {resources.getString(R.string.today)}
                    }
                }
            }).attach()
    }

    companion object {
        fun newInstance(i: Int) = PictureOfTheDayFragment(i)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}