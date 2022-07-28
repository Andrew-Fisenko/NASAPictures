package com.example.nasapictures.view.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nasapictures.MainActivity
import com.example.nasapictures.ThemeAqua
import com.example.nasapictures.ThemeViolet
import com.example.nasapictures.ThemeWalkman
import com.example.nasapictures.databinding.FragmentSettingsBinding
import com.example.nasapictures.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.tabs.TabLayout

class SettingsFragment : Fragment() {

    private lateinit var parentActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = (context as MainActivity)
    }

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTabLayoutClicks()
    }

    private fun setTabLayoutClicks() = with(binding) {
        when ((parentActivity.getCurrentTheme())) {
            ThemeViolet -> {
                settingLayout.selectTab(binding.settingLayout.getTabAt(0))
            }
            ThemeAqua -> {
                settingLayout.selectTab(binding.settingLayout.getTabAt(1))
            }
            ThemeWalkman -> {
                settingLayout.selectTab(binding.settingLayout.getTabAt(2))
            }
        }

        settingLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        parentActivity.setCurrentTheme(ThemeViolet)
                    }
                    1 -> {
                        parentActivity.setCurrentTheme(ThemeAqua)
                    }
                    2 -> {
                        parentActivity.setCurrentTheme(ThemeWalkman)
                    }
                }
                requireActivity().recreate()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}