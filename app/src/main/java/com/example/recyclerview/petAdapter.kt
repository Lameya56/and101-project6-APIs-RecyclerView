package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide



class PetAdapter (private val petList: List<String>): RecyclerView.Adapter<PetAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val petImage: ImageView

        init {
            // Find our RecyclerView item's ImageView for future use
            petImage = view.findViewById(R.id.pet_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetAdapter.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pet_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return petList.size
    }


    override fun onBindViewHolder(holder: PetAdapter.ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(petList[position])
            .centerCrop()
            .into(holder.petImage)
    }

}

