package com.example.nasapictures.view.navigation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
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

        binding.fab.setOnClickListener {
            isFlag = !isFlag
            if (isFlag) {
                ObjectAnimator.ofFloat(binding.fab, View.ROTATION, 0f, 720f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.textMars, View.ALPHA, 0f)
                    .setDuration(duration2).start()
                ObjectAnimator.ofFloat(binding.optionContainer, View.ALPHA, 1f)
                    .setDuration(duration).start()
            } else {
                ObjectAnimator.ofFloat(binding.fab, View.ROTATION, 720f, 0f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.textMars, View.ALPHA, 1f)
                    .setDuration(duration2).start()
                ObjectAnimator.ofFloat(binding.optionContainer, View.ALPHA, 0f)
                    .setDuration(duration).start()
            }
        }
    }

    fun rainbow(i: Int = 1) {
        var currentCount = i
        val x = object : CountDownTimer(20000, 200) {
            override fun onTick(millisUntilFinished: Long) {
                colorText(currentCount)
                currentCount = if (++currentCount > 5) 1 else currentCount
            }

            override fun onFinish() {
                rainbow(currentCount)
            }
        }
        x.start()
    }

    private fun colorText(colorFirstNumber: Int) {
        binding.textMars.setText(spannableRainbow, TextView.BufferType.SPANNABLE)
        spannableRainbow = binding.textMars.text as SpannableString
        val map = mapOf(
            0 to activity?.let { ContextCompat.getColor(it.applicationContext, R.color.red) },
            1 to activity?.let { ContextCompat.getColor(it.applicationContext, R.color.orange) },
            2 to activity?.let { ContextCompat.getColor(it.applicationContext, R.color.yellow) },
            3 to activity?.let { ContextCompat.getColor(it.applicationContext, R.color.green) },
            4 to activity?.let {
                ContextCompat.getColor(
                    it.applicationContext,
                    R.color.light_blue
                )
            },
            5 to activity?.let { ContextCompat.getColor(it.applicationContext, R.color.blue) },
            6 to activity?.let { ContextCompat.getColor(it.applicationContext, R.color.violet) }
        )
        val spans = spannableRainbow.getSpans(
            0, spannableRainbow.length,
            ForegroundColorSpan::class.java
        )
        for (span in spans) {
            spannableRainbow.removeSpan(span)
        }

        var colorNumber = colorFirstNumber
        for (i in 1 until (binding.textMars.text.length - 6) step 5) {
            if (colorNumber == 5) colorNumber = 0 else colorNumber += 1
            spannableRainbow.setSpan(
                map.getValue(colorNumber)?.let { ForegroundColorSpan(it) },
                i, i + 5,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
        }
    }

    companion object {
    }
}