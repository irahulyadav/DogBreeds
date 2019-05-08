package com.cabo.dogbreeds.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import com.cabo.dogbreeds.R
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.databinding.BreedFilterItemViewBinding
import com.cabo.dogbreeds.repository.ImageLoadListener
import com.cabo.dogbreeds.widget.BindingViewHolder

class BreedFilterAdapter() :
    PagedListAdapter<BreedEntity, BindingViewHolder<BreedFilterItemViewBinding>>(BreedDiffCallback()) {

    var imageLoadListener: ImageLoadListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<BreedFilterItemViewBinding> {
        return BindingViewHolder(
            DataBindingUtil.inflate<BreedFilterItemViewBinding>(
                LayoutInflater.from(parent.context), R.layout.breed_filter_item_view,
                parent,
                false
            ).root
        )
    }

    fun get(position: Int): BreedEntity {
        return getItem(position)!!
    }

    override fun onBindViewHolder(holder: BindingViewHolder<BreedFilterItemViewBinding>, position: Int) {
        val breed = getItem(position) ?: return
        holder.binding.breed = breed

        if (breed.image.isNullOrEmpty() && imageLoadListener != null) {
            imageLoadListener?.loadBreedImage(breed)
        }
    }

}