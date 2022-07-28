package com.example.nasapictures.view.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nasapictures.R
import com.example.nasapictures.databinding.ActivityBottomBarBinding
import com.google.android.material.badge.BadgeDrawable

class BottomBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.ThemeViolet)
        binding = ActivityBottomBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_view_earth -> {
                    navigateTo(EarthFragment()); true
                }
                R.id.action_view_mars -> {
                    navigateTo(MarsFragment()); true
                }
                R.id.action_view_system -> {
                    navigateTo(SystemFragment()); true
                }
                else -> true
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.action_view_earth

        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.action_view_system)
        badge.number = 1000
        badge.maxCharacterCount = 5
        badge.badgeGravity = BadgeDrawable.BOTTOM_START
        //binding.bottomNavigationView.removeBadge(R.id.action_view_system)

    }

    fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}
