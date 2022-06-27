package com.example.nasapictures.view


import android.os.Bundle

import android.view.View

import androidx.fragment.app.Fragment

import androidx.lifecycle.ViewModelProvider

import com.example.nasapictures.viewmodel.PicturesOfTheDayViewModel


class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        }
    }

    override fun onCreateView(){
        inflater.in

    }



    private var viewModel: PicturesOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PicturesOfTheDayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(
            viewLifecycleOwner
        ){appState -> rend
        }{it->
            when(it){
                is AppState.Error -> {}
                AppState.Loading -> {}
                is AppState.Success -> {
                    binding.imageView.load(it.pictureOfTheDayResponseData.utl)
                }}
            }
        }
    }

    companion object {
        fun newInstance = null

    }
}