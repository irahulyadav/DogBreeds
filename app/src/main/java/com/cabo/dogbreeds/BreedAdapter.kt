package com.cabo.dogbreeds

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.databinding.BreedItemViewBinding
import com.cabo.dogbreeds.widget.BindingViewHolder
import com.squareup.picasso.Picasso


class BreedAdapter : RecyclerView.Adapter<BindingViewHolder<BreedItemViewBinding>>() {

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
        holder.binding.breed = getItemCount(position)
    }

    override fun getItemCount(): Int {
        return breeds.count()
    }

    fun getItemCount(position: Int): BreedEntity {
        return breeds[position]
    }

}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imageUrl: String?) {
    Picasso.get()
        .load(imageUrl ?: "https://3c1703fe8d.site.internapcdn.net/newman/csz/news/800/2018/2-dog.jpg")
        .into(this)
}