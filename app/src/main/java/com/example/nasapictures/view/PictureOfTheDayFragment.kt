package com.example.nasapictures.view

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.nasapictures.databinding.FragmentPictureOfTheDayBinding
import com.example.nasapictures.viewmodel.AppState
import com.example.nasapictures.viewmodel.PictureOfTheDayViewModel
import java.text.SimpleDateFormat

import java.util.*

class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner) { appState ->
            renderData(appState)
        }
        viewModel.sendRequest()

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

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Error -> {

            }
            AppState.Loading -> {

            }
            is AppState.Success -> {
                binding.imageView.load(appState.PictureOfTheDayResponceData.url) {
                    //настроить загрузку изображения error placeholder
                }
            }
        }
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




