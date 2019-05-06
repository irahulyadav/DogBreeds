package com.cabo.dogbreeds

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.databinding.BreedItemViewBinding
import com.cabo.dogbreeds.viewmodel.BreedViewModel
import com.cabo.dogbreeds.widget.BindingViewHolder
import com.squareup.picasso.Picasso


class BreedAdapter(val breedViewModel: BreedViewModel) :
    PagedListAdapter<BreedEntity, BindingViewHolder<BreedItemViewBinding>>(BreedDiffCallback()) {

    var breeds: List<BreedEntity> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


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

    override fun onBindViewHolder(holder: BindingViewHolder<BreedItemViewBinding>, position: Int) {
        val breed = getItemCount(position)
        holder.binding.breed = breed
    }

    fun loadBreedImage(breedEntity: BreedEntity) {
        if (breedEntity.image.isNullOrEmpty()) {
            breedViewModel.breedRepository.loadBreedImage(breedEntity)
        }
    }

    override fun getItemCount(): Int {
        return breeds.count()
    }

    fun getItemCount(position: Int): BreedEntity {
        return breeds[position]
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(imageView: ImageView, breedEntity: BreedEntity) {
            Picasso.get()
                .load(if (breedEntity.image.isNullOrEmpty()) "https://3c1703fe8d.site.internapcdn.net/newman/csz/news/800/2018/2-dog.jpg" else breedEntity.image)
                .into(imageView)
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