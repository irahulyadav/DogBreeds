package com.cabo.dogbreeds.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cabo.dogbreeds.R
import com.cabo.dogbreeds.databinding.ImageItemViewBinding
import com.cabo.dogbreeds.widget.BindingViewHolder

class ImageListAdapter : RecyclerView.Adapter<BindingViewHolder<ImageItemViewBinding>>() {

    var list: List<String> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ImageItemViewBinding> {
        return BindingViewHolder(
            DataBindingUtil.inflate<ImageItemViewBinding>(
                LayoutInflater.from(parent.context),
                R.layout.image_item_view,
                parent,
                false
            ).root
        )
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ImageItemViewBinding>, position: Int) {
        holder.binding.image = list[position]
    }


    override fun getItemCount(): Int {
        return list.count()
    }
}