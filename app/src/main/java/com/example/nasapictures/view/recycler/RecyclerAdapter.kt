package com.example.nasapictures.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nasapictures.R
import com.example.nasapictures.databinding.ActivityRecyclerItemEarthBinding
import com.example.nasapictures.databinding.ActivityRecyclerItemHeaderBinding
import com.example.nasapictures.databinding.ActivityRecyclerItemMarsBinding
import com.example.nasapictures.model.AddItem
import com.example.nasapictures.model.RemoveItem
import com.example.nasapictures.view.recycler.diffutils.Change
import com.example.nasapictures.view.recycler.diffutils.DiffUtilCallback
import com.example.nasapictures.view.recycler.diffutils.createCombinedPayload
import com.google.android.material.snackbar.Snackbar

class RecyclerAdapter(
    private var listData: MutableList<Pair<Data, Boolean>>,
    val callbackAddEarth: AddItem,
    val callbackAddMars: AddItem,
    val callbackRemove: RemoveItem
) :
    RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>(), ItemTouchHelperAdapter {

    fun setListDataRemoveForDiffUtil(listDataNew: MutableList<Pair<Data, Boolean>>) {
        val diff = DiffUtil.calculateDiff(DiffUtilCallback(listData, listDataNew))
        diff.dispatchUpdatesTo(this)
        listData = listDataNew
    }

    fun setListDataRemove(listDataNew: MutableList<Pair<Data, Boolean>>, position: Int) {
        listData = listDataNew
        notifyItemRemoved(position)
    }

    fun setListDataAdd(listDataNew: MutableList<Pair<Data, Boolean>>, position: Int) {
        listData = listDataNew
        notifyItemInserted(position)
    }

    override fun getItemViewType(position: Int): Int {
        return listData[position].first.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
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

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val createCombinedPayload =
                createCombinedPayload(payloads as List<Change<Pair<Data, Boolean>>>)
            if (createCombinedPayload.newData.first.name != createCombinedPayload.oldData.first.name)
                holder.itemView.findViewById<TextView>(R.id.name).text =
                    createCombinedPayload.newData.first.name
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class HeaderViewHolder(val binding: ActivityRecyclerItemHeaderBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(data: Pair<Data, Boolean>) {
            binding.name.text = data.first.name
        }
    }

    inner class EarthViewHolder(val binding: ActivityRecyclerItemEarthBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(data: Pair<Data, Boolean>) {
            binding.name.text = data.first.name
            binding.addItemImageView.setOnClickListener {
                callbackAddEarth.add(layoutPosition)
            }
            binding.removeItemImageView.setOnClickListener {
                callbackRemove.remove(layoutPosition)
            }
            binding.moveItemUp.setOnClickListener {
                if (layoutPosition > 1) {
                    listData.removeAt(layoutPosition).apply {
                        listData.add(layoutPosition - 1, this)
                    }
                    notifyItemMoved(layoutPosition, layoutPosition - 1)
                } else {
                    Snackbar.make(binding.name, "Not available to move!", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
            binding.moveItemDown.setOnClickListener {
                if (layoutPosition < listData.size - 1) {
                    listData.removeAt(layoutPosition).apply {
                        listData.add(layoutPosition + 1, this)
                    }
                    notifyItemMoved(layoutPosition, layoutPosition + 1)
                } else {
                    Snackbar.make(binding.name, "Not available to move!", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
            binding.earthDescriptionTextView.visibility =
                if (listData[layoutPosition].second) View.VISIBLE else View.GONE

            binding.earthImageView.setOnClickListener {
                listData[layoutPosition] = listData[layoutPosition].let {
                    it.first to !it.second
                }
                notifyItemChanged(layoutPosition)
            }
        }
    }

    inner class MarsViewHolder(val binding: ActivityRecyclerItemMarsBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(data: Pair<Data, Boolean>) {
            binding.name.text = data.first.name
            binding.addItemImageView.setOnClickListener {
                callbackAddMars.add(layoutPosition)
            }
            binding.removeItemImageView.setOnClickListener {
                callbackRemove.remove(layoutPosition)
            }
            binding.moveItemUp.setOnClickListener {
                if (layoutPosition > 1) {
                    listData.removeAt(layoutPosition).apply {
                        listData.add(layoutPosition - 1, this)
                    }
                    notifyItemMoved(layoutPosition, layoutPosition - 1)
                } else {
                    Snackbar.make(binding.name, "Not available to move!", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
            binding.moveItemDown.setOnClickListener {
                if (layoutPosition < listData.size - 1) {
                    listData.removeAt(layoutPosition).apply {
                        listData.add(layoutPosition + 1, this)
                    }
                    notifyItemMoved(layoutPosition, layoutPosition + 1)
                } else {
                    Snackbar.make(binding.name, "Not available to move!", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
            binding.marsDescriptionTextView.visibility =
                if (listData[layoutPosition].second) View.VISIBLE else View.GONE

            binding.marsImageView.setOnClickListener {
                listData[layoutPosition] = listData[layoutPosition].let {
                    it.first to !it.second
                }
                notifyItemChanged(layoutPosition)
            }
        }
    }

    abstract class BaseViewHolder(view: View) :
        RecyclerView.ViewHolder(view), ItemTouchHelperViewHolder {
        abstract fun bind(data: Pair<Data, Boolean>)

        override fun onItemSelect() {
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.teal_200))
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        listData.removeAt(fromPosition).apply {
            listData.add(toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        callbackRemove.remove(position)
    }
}