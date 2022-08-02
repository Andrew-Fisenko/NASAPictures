package com.example.nasapictures.view.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nasapictures.databinding.FragmentNotesBinding
import com.example.nasapictures.view.picture.PictureOfTheDayFragment

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arrayListOf(
            Data("Заголовок", type = TYPE_HEADER),
            Data("Earth", type = TYPE_EARTH),
            Data("Earth", type = TYPE_EARTH),
            Data("Mars", type = TYPE_MARS),
            Data("Earth", type = TYPE_EARTH),
            Data("Earth", type = TYPE_EARTH),
            Data("Earth", type = TYPE_EARTH),
            Data("Mars", type = TYPE_MARS)
        )
        binding.recyclerView.adapter = RecyclerAdapter(data)
    }

    companion object {
        fun newInstance(i: Int) = PictureOfTheDayFragment(i)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
