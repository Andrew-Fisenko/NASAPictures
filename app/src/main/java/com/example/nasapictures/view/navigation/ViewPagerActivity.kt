package com.example.nasapictures.view.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nasapictures.R
import com.example.nasapictures.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.ThemeViolet)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)


    }
}