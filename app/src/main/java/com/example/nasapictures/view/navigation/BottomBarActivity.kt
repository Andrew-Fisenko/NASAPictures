package com.example.nasapictures.view.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nasapictures.R
import com.example.nasapictures.databinding.ActivityBottomBarBinding
import com.example.nasapictures.view.SplashFragment
import com.example.nasapictures.view.recycler.NotesFragment
import com.google.android.material.badge.BadgeDrawable

class BottomBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomBarBinding

    @SuppressLint("ResourceType")
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
                R.id.action_view_notes -> {
                    navigateTo(NotesFragment()); true
                }
                R.id.action_loading -> {
                    navigateTo(SplashFragment()); true
                }
                else -> true
            }
        }
        binding.bottomNavigationView.visibility = View.INVISIBLE
        binding.bottomNavigationView.selectedItemId = R.id.action_loading

        Handler(Looper.getMainLooper()).postDelayed({
            binding.bottomNavigationView.visibility = View.VISIBLE
            binding.bottomNavigationView.selectedItemId = R.id.action_view_earth
        }, 3000L)

        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.action_view_notes)
        badge.number = 10
        badge.maxCharacterCount = 5
        badge.badgeGravity = BadgeDrawable.BOTTOM_START
    }

    private fun navigateTo(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction();
        ft.setCustomAnimations(
            R.anim.slide_in,
            R.anim.slide_out,
            R.anim.fade_in,
            R.anim.fade_out
        );
        ft.replace(R.id.container, fragment).commit()
    }
}
