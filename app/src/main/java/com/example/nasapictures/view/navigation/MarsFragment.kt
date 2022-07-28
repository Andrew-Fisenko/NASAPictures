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
import com.example.nasapictures.databinding.FragmentMarsBinding

class MarsFragment : Fragment() {

    private var _binding: FragmentMarsBinding? = null
    private val binding get() = _binding!!

    var isFlag = false
    var isFlag2 = false
    var duration = 2000L
    var duration2 = 1000L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val constraintSet = ConstraintSet()
        constraintSet.clone(context, R.layout.fragment_mars)

        binding.nestedScrollViewMars.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            binding.appBar.isSelected = binding.nestedScrollViewMars.canScrollVertically(-1)
        }

        binding.marsImage.setOnClickListener {
            isFlag2 = !isFlag2
            val changeBounds = ChangeBounds()
            changeBounds.duration = 2000L
            changeBounds.interpolator = AnticipateOvershootInterpolator(2.0f)
            TransitionManager.beginDelayedTransition(binding.titleContainer, changeBounds)
            if (isFlag2) {
                constraintSet.connect(
                    R.id.marsTitle,
                    ConstraintSet.RIGHT, R.id.titleContainer,
                    ConstraintSet.RIGHT
                )
            } else {
                constraintSet.connect(
                    R.id.marsTitle,
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
                ObjectAnimator.ofFloat(binding.textMars, View.ALPHA, 0f)
                    .setDuration(duration2).start()
                ObjectAnimator.ofFloat(binding.optionContainer, View.ALPHA, 1f)
                    .setDuration(duration).start()
//                binding.longText.animate().alpha(0f).setDuration(duration2).setListener(
//                    object : AnimatorListenerAdapter() {
//                        override fun onAnimationEnd(animation: Animator) {
//                            binding.longText.isClickable = true
//                        }
//                    }
//                )
//              binding.optionContainer.animate().alpha(1f).setDuration(duration).setListener(
//                    object : AnimatorListenerAdapter() {
//                        override fun onAnimationEnd(animation: Animator) {
//                            binding.optionContainer.isClickable = false
//                        }
//                    }
//                )
//
            } else {
                ObjectAnimator.ofFloat(binding.fab, View.ROTATION, 720f, 0f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.textMars, View.ALPHA, 1f)
                    .setDuration(duration2).start()
                ObjectAnimator.ofFloat(binding.optionContainer, View.ALPHA, 0f)
                    .setDuration(duration).start()
//                binding.optionContainer.animate().alpha(0f).setDuration(duration).setListener(
//                    object : AnimatorListenerAdapter() {
//                        override fun onAnimationEnd(animation: Animator) {
//                            binding.optionContainer.isClickable = false
//                        }
//                    }
//                )
//                binding.longText.animate().alpha(1f).setDuration(duration2).setListener(
//                    object : AnimatorListenerAdapter() {
//                        override fun onAnimationEnd(animation: Animator) {
//                            binding.longText.isClickable = true
//                        }
//
//                    }
//                )
            }
        }
    }

    companion object {
    }
}