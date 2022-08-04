package com.example.nasapictures.view.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.nasapictures.databinding.FragmentNotesBinding
import com.example.nasapictures.model.AddItem
import com.example.nasapictures.model.RemoveItem
import com.example.nasapictures.view.picture.PictureOfTheDayFragment

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: RecyclerAdapter
    private var isNewList = false
    val data = arrayListOf(
        Pair(Data(id=0,"Заголовок", type = TYPE_HEADER), false),
        Pair(Data(id=1,"Earth", type = TYPE_EARTH), false),
        Pair(Data(id=2,"Earth", type = TYPE_EARTH), false),
        Pair(Data(id=3,"Mars", type = TYPE_MARS), false),
        Pair(Data(id=4,"Earth", type = TYPE_EARTH), false),
        Pair(Data(id=5,"Earth", type = TYPE_EARTH), false),
        Pair(Data(id=6,"Earth", type = TYPE_EARTH), false),
        Pair(Data(id=7,"Mars", type = TYPE_MARS), false),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RecyclerAdapter(data, callbackAddEarth, callbackAddMars, callbackRemove)
        binding.recyclerView.adapter = adapter

        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView((binding.recyclerView))
        binding.recyclerActivityDiffUtilFAB.setOnClickListener {
            changeAdapterData()
        }
    }

    private val callbackAddEarth = AddItem() {
        data.add(it, Pair(Data(id=0,"Earth(New)", type = TYPE_EARTH), false))
        adapter.setListDataAdd(data, it)
    }

    private val callbackAddMars = AddItem() {
        data.add(it, Pair(Data(id=0,"Mars(New)", type = TYPE_MARS), false))
        adapter.setListDataAdd(data, it)
    }

    private val callbackRemove = RemoveItem {
        data.removeAt(it)
        adapter.setListDataRemove(data, it)
    }


    private fun changeAdapterData() {
        adapter.setListDataRemoveForDiffUtil(createItemList(isNewList).map { it }.toMutableList())
        isNewList = !isNewList
    }

    private fun createItemList(instanceNumber: Boolean): List<Pair<Data, Boolean>> {
        return when (instanceNumber) {
            false -> listOf(
                Pair(Data(0, "Header", type = TYPE_HEADER), false),
                Pair(Data(1, "Mars", ""), false),
                Pair(Data(2, "Mars", ""), false),
                Pair(Data(3, "Mars", ""), false),
                Pair(Data(4, "Mars", ""), false),
                Pair(Data(5, "Mars", ""), false),
                Pair(Data(6, "Mars", ""), false)
            )
            true -> listOf(
                Pair(Data(0, "Header",type = TYPE_HEADER), false),
                Pair(Data(1, "Mars", ""), false),
                Pair(Data(2, "Jupiter", ""), false),
                Pair(Data(3, "Mars", ""), false),
                Pair(Data(4, "Neptune", ""), false),
                Pair(Data(5, "Saturn", ""), false),
                Pair(Data(6, "Mars", ""), false)
            )
        }
    }

    companion object {
        fun newInstance(i: Int) = PictureOfTheDayFragment(i)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}