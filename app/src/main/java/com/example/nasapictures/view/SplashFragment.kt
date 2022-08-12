package com.example.nasapictures.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nasapictures.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    val duration = 2000L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ObjectAnimator.ofFloat(binding.icon, View.ROTATION, 0f, 720f)
            .setDuration(duration).start()

//        val constraintSet = ConstraintSet()
//        constraintSet.clone(context, R.layout.fragment_splash)
//        binding.icon.animate().rotation(7200f).setDuration(20000L).start()


//
//        Handler(Looper.getMainLooper()).postDelayed({
//            startActivity(Intent(this,UXActivity::class.java))
//
//        },2000L)

    }
}



