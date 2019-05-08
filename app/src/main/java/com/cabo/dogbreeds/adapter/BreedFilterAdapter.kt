package com.cabo.dogbreeds.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cabo.dogbreeds.R
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.databinding.BreedFilterItemViewBinding
import com.cabo.dogbreeds.widget.BindingViewHolder

class BreedFilterAdapter() :
    RecyclerView.Adapter<BindingViewHolder<BreedFilterItemViewBinding>>() {

    var list: List<BreedEntity> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<BreedFilterItemViewBinding> {
        return BindingViewHolder(
            DataBindingUtil.inflate<BreedFilterItemViewBinding>(
                LayoutInflater.from(parent.context), R.layout.breed_filter_item_view,
                parent,
                false
            ).root
        )
    }

    fun getItem(position: Int): BreedEntity {
        return list[position]
    }

    override fun onBindViewHolder(holder: BindingViewHolder<BreedFilterItemViewBinding>, position: Int) {
        val breed = getItem(position) ?: return
        holder.binding.breed = breed
    }

    override fun getItemCount(): Int {
        return list.count()
    }

}