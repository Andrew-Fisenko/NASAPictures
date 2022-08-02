package com.example.nasapictures.view.navigation


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nasapictures.databinding.ActivityRecyclerItemEarthBinding
import com.example.nasapictures.databinding.ActivityRecyclerItemHeaderBinding
import com.example.nasapictures.databinding.ActivityRecyclerItemMarsBinding

class RecyclerAdapter(private val listData: List<Data>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class HeaderViewHolder(val binding: ActivityRecyclerItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    class EarthViewHolder(val binding: ActivityRecyclerItemEarthBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    class MarsViewHolder(val binding: ActivityRecyclerItemMarsBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}