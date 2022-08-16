package com.example.nasapictures

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.nasapictures.databinding.ActivityMainBinding
import com.example.nasapictures.view.SplashFragment
import com.example.nasapictures.view.picture.PictureOfTheDayFragment

const val ThemeViolet = 1
const val ThemeAqua = 2
const val ThemeWalkman = 3

class MainActivity : AppCompatActivity() {

    private val KEY_SP = "sp"
    private val KEY_CURRENT_THEME = "current_theme"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(getRealStyle(getCurrentTheme()))

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.container, SplashFragment()).commit()
        Handler(Looper.getMainLooper()).postDelayed({
            if (savedInstanceState == null) {
                val i = 0
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PictureOfTheDayFragment.newInstance(i)).commit()
            }
        }, 2000L)

        if (savedInstanceState == null) {
            val i = 0
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance(i)).commit()
        }
    }

    fun setCurrentTheme(currentTheme: Int) {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_CURRENT_THEME, currentTheme)
        editor.apply()
    }

    fun getCurrentTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME, -1)
    }

    private fun getRealStyle(currentTheme: Int): Int {
        return when (currentTheme) {
            ThemeViolet -> R.style.ThemeViolet
            ThemeAqua -> R.style.ThemeAqua
            ThemeWalkman -> R.style.ThemeWalkman
            else -> R.style.ThemeWalkman
        }
    }
}