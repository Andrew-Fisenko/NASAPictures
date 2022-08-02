package com.example.nasapictures.view.navigation


import android.view.LayoutInflater
<<<<<<< HEAD
import android.view.View
=======
>>>>>>> origin/Lesson_6
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nasapictures.databinding.ActivityRecyclerItemEarthBinding
import com.example.nasapictures.databinding.ActivityRecyclerItemHeaderBinding
import com.example.nasapictures.databinding.ActivityRecyclerItemMarsBinding

class RecyclerAdapter(private val listData: List<Data>) :
<<<<<<< HEAD
    RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>() {
=======
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
>>>>>>> origin/Lesson_6

    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

<<<<<<< HEAD
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  BaseViewHolder {
=======
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
>>>>>>> origin/Lesson_6
        return when (viewType) {
            TYPE_EARTH -> {
                val binding =
                    ActivityRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context))
                EarthViewHolder(binding)
            }
            TYPE_MARS -> {
                val binding =
                    ActivityRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context))
                MarsViewHolder(binding)
            }
            else -> {
                val binding =
                    ActivityRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context))
                HeaderViewHolder(binding)
            }
        }
    }

<<<<<<< HEAD
    override fun onBindViewHolder(holder:  BaseViewHolder, position: Int) {
       holder.bind(listData[position])
=======
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //TODO("Not yet implemented")
>>>>>>> origin/Lesson_6
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class HeaderViewHolder(val binding: ActivityRecyclerItemHeaderBinding) :
<<<<<<< HEAD
        BaseViewHolder(binding.root) {
        override fun bind(data:Data){
            binding.name.text = data.name
        }
    }

    class EarthViewHolder(val binding: ActivityRecyclerItemEarthBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(data:Data){
            binding.name.text = data.name
        }
    }

    class MarsViewHolder(val binding: ActivityRecyclerItemMarsBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(data:Data){
            binding.name.text = data.name
        }
    }

    abstract class BaseViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Data)
    }
}}
=======
        RecyclerView.ViewHolder(binding.root) {
    }

    class EarthViewHolder(val binding: ActivityRecyclerItemEarthBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    class MarsViewHolder(val binding: ActivityRecyclerItemMarsBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}
>>>>>>> origin/Lesson_6
