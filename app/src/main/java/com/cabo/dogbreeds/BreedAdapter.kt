package com.cabo.dogbreeds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.squareup.picasso.Picasso

class BreedAdapter : RecyclerView.Adapter<BreedAdapter.BreedHolder>() {

    var breeds: List<BreedEntity> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedHolder {
        return BreedHolder(LayoutInflater.from(parent.context).inflate(R.layout.breed_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: BreedHolder, position: Int) {
        holder.breedEntity = getItemCount(position)
    }

    override fun getItemCount(): Int {
        return breeds.count()
    }

    fun getItemCount(position: Int): BreedEntity {
        return breeds[position]
    }


    class BreedHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val ivImage = itemView.findViewById<ImageView>(R.id.ivImage)

        var breedEntity: BreedEntity? = null
            set(value) {
                field = value
                if (value == null) {
                    return
                }
                tvName.text = value.breed
                if (value.image != null) {
                    Picasso.get()
                        .load(value.image)
                        .into(ivImage)
                }

            }

    }
}