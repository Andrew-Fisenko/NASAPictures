package com.example.nasapictures.view.navigation

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.nasapictures.R
import com.example.nasapictures.databinding.FragmentMarsBinding

class MarsFragment : Fragment() {

    private var _binding: FragmentMarsBinding? = null
    private val binding get() = _binding!!
    lateinit var spannableRainbow: SpannableString

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
            binding.header.isSelected = binding.nestedScrollViewMars.canScrollVertically(-1)
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



        spannableRainbow = SpannableString(getString(R.string.large_text))
        rainbow(1)
//        val text = binding.textMars.text
//        val spannableString = SpannableString(text)
//        for (i in text.indices){
//            if(text[i] != ' '){
//                spannableString.setSpan(
//                    ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.teal_700)),
//                    i,i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                spannableString.setSpan(
//                    AbsoluteSizeSpan(32,true ), i,i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//            } else break
//        }
//        binding.textMars.text = spannableString

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

    fun rainbow(i:Int=1) {
        var currentCount = i
        val x = object : CountDownTimer(20000, 200) {
            override fun onTick(millisUntilFinished: Long) {
                colorText(currentCount)
                currentCount = if (++currentCount>5) 1 else currentCount
            }
            override fun onFinish() {
                rainbow(currentCount)
            }
        }
        x.start()


    }


    private fun colorText(colorFirstNumber:Int){
        binding.textMars.setText(spannableRainbow, TextView.BufferType.SPANNABLE)
        spannableRainbow = binding.textMars.text as SpannableString
        val map = mapOf(
            0 to ContextCompat.getColor(requireContext(), R.color.red),
            1 to ContextCompat.getColor(requireContext(), R.color.orange),
            2 to ContextCompat.getColor(requireContext(), R.color.yellow),
            3 to ContextCompat.getColor(requireContext(), R.color.green),
            4 to ContextCompat.getColor(requireContext(), R.color.light_blue),
            5 to ContextCompat.getColor(requireContext(), R.color.blue),
            6 to ContextCompat.getColor(requireContext(),R.color.violet)
        )
        val spans = spannableRainbow.getSpans(
            0, spannableRainbow.length,
            ForegroundColorSpan::class.java
        )
        for (span in spans) {
            spannableRainbow.removeSpan(span)
        }

        var colorNumber = colorFirstNumber
        for (i in 0 until binding.textMars.text.length) {
            if (colorNumber == 5) colorNumber = 0 else colorNumber += 1
            spannableRainbow.setSpan(
                ForegroundColorSpan(map.getValue(colorNumber)),
                i, i + 1,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
        }
    }

    companion object {
    }
}