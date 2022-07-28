package com.example.nasapictures.view.navigation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nasapictures.databinding.FragmentSystemBinding

class SystemFragment : Fragment() {

    private var _binding: FragmentSystemBinding? = null
    private val binding get() = _binding!!

    var isFlag = false
    var duration = 2000L
    var duration2 = 1000L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSystemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            isFlag = !isFlag
            if (isFlag) {
                ObjectAnimator.ofFloat(binding.fab, View.ROTATION, 0f, 720f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.textSystem, View.ALPHA, 0f)
                    .setDuration(duration2).start()
                ObjectAnimator.ofFloat(binding.optionContainer, View.ALPHA, 1f)
                    .setDuration(duration).start()

            } else {
                ObjectAnimator.ofFloat(binding.fab, View.ROTATION, 720f, 0f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.textSystem, View.ALPHA, 1f)
                    .setDuration(duration2).start()
                ObjectAnimator.ofFloat(binding.optionContainer, View.ALPHA, 0f)
                    .setDuration(duration).start()
            }
        }
    }

    companion object {
    }
}