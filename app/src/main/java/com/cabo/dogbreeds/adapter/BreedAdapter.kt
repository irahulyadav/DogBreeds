package com.cabo.dogbreeds.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.cabo.dogbreeds.R
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.databinding.BreedItemViewBinding
import com.cabo.dogbreeds.repository.ImageLoadListener
import com.cabo.dogbreeds.widget.BindingViewHolder
import com.squareup.picasso.Picasso


class BreedAdapter() : PagedListAdapter<BreedEntity, BindingViewHolder<BreedItemViewBinding>>(BreedDiffCallback()) {

    var imageLoadListener: ImageLoadListener? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<BreedItemViewBinding> {
        return BindingViewHolder(
            DataBindingUtil.inflate<BreedItemViewBinding>(
                LayoutInflater.from(parent.context),
                R.layout.breed_item_view,
                parent,
                false
            ).root
        )
    }

    fun get(position: Int): BreedEntity {
        return getItem(position)!!
    }

    override fun onBindViewHolder(holder: BindingViewHolder<BreedItemViewBinding>, position: Int) {
        val breed = getItem(position) ?: return
        holder.binding.breed = breed
        print(position)
        if (breed.image.isNullOrEmpty() && imageLoadListener != null) {
            imageLoadListener?.loadBreedImage(breed) {
                holder.binding.ivImage.setImageUrl(breed.image)
            }
        }
    }

}



class BreedDiffCallback : DiffUtil.ItemCallback<BreedEntity>() {

    override fun areItemsTheSame(oldItem: BreedEntity, newItem: BreedEntity): Boolean {
        return oldItem.breed == newItem.breed
    }

    override fun areContentsTheSame(oldItem: BreedEntity, newItem: BreedEntity): Boolean {
        return oldItem == newItem
    }
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imageUrl: String?) {
    Picasso.get()
        .load(if (imageUrl.isNullOrEmpty()) "https://3c1703fe8d.site.internapcdn.net/newman/csz/news/800/2018/2-dog.jpg" else imageUrl)
        .into(this)
}