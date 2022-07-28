package com.example.nasapictures.view.navigation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.nasapictures.R
import com.example.nasapictures.databinding.FragmentSystemBinding

class SystemFragment : Fragment() {

    private var _binding: FragmentSystemBinding? = null
    private val binding get() = _binding!!

    var isFlag = false
    var isFlag2 = false
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

        val constraintSet = ConstraintSet()
        constraintSet.clone(context, R.layout.fragment_system)

        binding.nestedScrollViewSystem.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            binding.header.isSelected = binding.nestedScrollViewSystem.canScrollVertically(-1)
        }

        binding.systemImage.setOnClickListener {
            isFlag2 = !isFlag2
            val changeBounds = ChangeBounds()
            changeBounds.duration = 2000L
            changeBounds.interpolator = AnticipateOvershootInterpolator(2.0f)
            TransitionManager.beginDelayedTransition(binding.titleContainer, changeBounds)
            if (isFlag2) {
                constraintSet.connect(
                    R.id.systemTitle,
                    ConstraintSet.RIGHT, R.id.titleContainer,
                    ConstraintSet.RIGHT
                )
            } else {
                constraintSet.connect(
                    R.id.systemTitle,
                    ConstraintSet.RIGHT, R.id.titleContainer,
                    ConstraintSet.LEFT
                )
            }
            constraintSet.applyTo(binding.titleContainer)
        }

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