package com.naga.recipefinder.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naga.recipefinder.R
import com.naga.recipefinder.models.Hit

class RecipeRecyclerAdapter(
    private val onSearchListener: OnSearchListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: List<Hit> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)
        return RecipeViewHolder(view, onSearchListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemView = (holder as RecipeViewHolder).itemView

        val image = itemView.findViewById<ImageView>(R.id.recipeImage)
        Glide.with(itemView)
            .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_find_recipes))
            .load(list[position].recipe.image)
            .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
            .into(image)
        image.contentDescription = list[position].recipe.label

        itemView.findViewById<TextView>(R.id.recipeName).text =
            list[position].recipe.label
    }
}
