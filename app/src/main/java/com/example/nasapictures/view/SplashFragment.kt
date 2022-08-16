package com.example.nasapictures.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nasapictures.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    val duration = 3000f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ObjectAnimator.ofFloat(binding.icon, View.ROTATION, 0f, 1440f)
            .setDuration(duration.toLong()).start()
        val progress = binding.progress

        object : CountDownTimer(duration.toLong(), 1L) {
            override fun onTick(millisUntilFinished: Long) {
                val process = ((1 - millisUntilFinished / duration) * 100).toInt()
                if (progress.progress != process)
                    progress.progress = process
            }

            override fun onFinish() {
            }
        }.start()
    }
}



