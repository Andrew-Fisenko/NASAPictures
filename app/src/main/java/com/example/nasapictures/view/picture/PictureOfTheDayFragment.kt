package com.example.nasapictures.view.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.example.nasapictures.R
import com.example.nasapictures.databinding.FragmentPictureOfTheDayBinding
import com.example.nasapictures.view.drawer.BottomNavigationDrawerFragment
import com.example.nasapictures.view.settings.SettingsFragment
import com.example.nasapictures.viewmodel.AppState
import com.example.nasapictures.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*

class PictureOfTheDayFragment(i: Int) : Fragment() {

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding get() = _binding!!
    private var date = i
    var isFlag = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.getLiveData().observe(viewLifecycleOwner) { appState ->
            renderData(appState)
        }
        viewModel.sendRequestByDate(newDate(date))

        binding.chipTwoDaysAgo.setOnClickListener {
            viewModel.sendRequestByDate(newDate(2))
            Toast.makeText(requireContext(), "chipTwoDaysAgo", Toast.LENGTH_SHORT).show()
        }
        binding.chipYesterday.setOnClickListener {
            viewModel.sendRequestByDate(newDate(1))
            Toast.makeText(requireContext(), "chipYesterday", Toast.LENGTH_SHORT).show()
        }
        binding.chipToday.setOnClickListener {
            viewModel.sendRequestByDate(newDate(0))
            Toast.makeText(requireContext(), "chipToday", Toast.LENGTH_SHORT).show()
        }
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${binding.input.text.toString()}")
            })
        }


        binding.imageView.setOnClickListener {
            isFlag = !isFlag

            val params = it.layoutParams as ConstraintLayout.LayoutParams

            val transitionSet = TransitionSet()
            val changeImageTransform = ChangeImageTransform()
            val changeBounds = ChangeBounds()
            changeBounds.duration = 2000L
            changeImageTransform.duration = 2000L

            transitionSet.ordering = TransitionSet.ORDERING_TOGETHER

            transitionSet.addTransition(changeBounds)// важен порядок, обязетельно changeImageTransform после changeBounds
            transitionSet.addTransition(changeImageTransform)

            TransitionManager.beginDelayedTransition(binding.root, transitionSet)
            if (isFlag) {
                params.height = LinearLayout.LayoutParams.MATCH_PARENT
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            } else {
                params.height = LinearLayout.LayoutParams.WRAP_CONTENT
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
            binding.imageView.layoutParams = params
        }



//        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
//        setHasOptionsMenu(true)
}

override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.menu_main, menu)
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
        R.id.action_favorite -> {}
        R.id.action_settings -> {
            requireActivity().supportFragmentManager.beginTransaction().hide(this)
                .add(R.id.container, SettingsFragment.newInstance()).addToBackStack("").commit()
        }
        android.R.id.home -> {
            activity?.let {
                BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
            }
        }
    }
    return super.onOptionsItemSelected(item)
}


private fun newDate(i: Int): String {
    val now = Calendar.getInstance()
    val year = now.get(Calendar.YEAR)
    val month = now.get(Calendar.MONTH)
    val day = now.get(Calendar.DAY_OF_MONTH)
    return if (day - i > 0) {
        "${year}-${month + 1}-${day - i}"
    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
        "${year}-${month}-${31 - i}"
    } else if (month == 2) {
        "${year}-${month}-${29 - i}"
    } else {
        "${year}-${month}-${32 - i}"
    }
}

private fun renderData(appState: AppState) {
    when (appState) {
        is AppState.Error -> {
            binding.loadingLayout.visibility = View.GONE
            binding.imageView.setImageResource(R.drawable.ic_load_error_vector)
            val throwable = appState.error
            Snackbar.make(binding.imageView, "Error $throwable", Snackbar.LENGTH_LONG).show()
        }
        is AppState.Loading -> {
            binding.loadingLayout.visibility = View.VISIBLE
            binding.imageView.setImageResource(R.drawable.ic_no_photo_vector)
        }
        is AppState.Success -> {
            binding.loadingLayout.visibility = View.GONE
            if (appState.pictureOfTheDayResponseData.mediaType == "video") {
                showAVideoUrl(appState.pictureOfTheDayResponseData.url)
            } else {
                binding.imageView.load(appState.pictureOfTheDayResponseData.url)
                Snackbar.make(binding.imageView, "Success", Snackbar.LENGTH_LONG).show()

            }


        }
    }
}

companion object {
    fun newInstance(i: Int) = PictureOfTheDayFragment(i)
}

private fun showAVideoUrl(videoUrl: String) = with(binding) {
    with(binding) {
        imageView.visibility = View.GONE
        videoOfTheDay.visibility = View.VISIBLE
        imageViewSmall.visibility = View.VISIBLE
        binding.imageViewSmall.setImageResource(R.drawable.ic_no_photo_vector)
        videoOfTheDay.text = "We do not have any pictures today, but we have a video! " +
                "\n Tap HERE to watch"
        videoOfTheDay.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(videoUrl)
            }
            startActivity(i)
        }
    }
}

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}
}




