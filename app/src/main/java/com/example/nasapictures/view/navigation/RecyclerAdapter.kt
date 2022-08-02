package com.example.nasapictures.view.navigation


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nasapictures.databinding.ActivityRecyclerItemEarthBinding
import com.example.nasapictures.databinding.ActivityRecyclerItemHeaderBinding
import com.example.nasapictures.databinding.ActivityRecyclerItemMarsBinding

class RecyclerAdapter(private val listData: List<Data>) :
    RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  BaseViewHolder {
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

    override fun onBindViewHolder(holder:  BaseViewHolder, position: Int) {
       holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class HeaderViewHolder(val binding: ActivityRecyclerItemHeaderBinding) :
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