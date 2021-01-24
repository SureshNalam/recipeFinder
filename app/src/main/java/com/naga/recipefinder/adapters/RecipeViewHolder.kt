package com.naga.recipefinder.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.naga.recipefinder.adapters.OnSearchListener

class RecipeViewHolder(itemView: View, private val searchListener: OnSearchListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        searchListener.onSearchResultClick(adapterPosition)
    }
}
