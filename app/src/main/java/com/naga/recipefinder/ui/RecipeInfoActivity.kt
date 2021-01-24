package com.naga.recipefinder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naga.recipefinder.R
import com.naga.recipefinder.databinding.ActivityRecipeInfoBinding
import com.naga.recipefinder.models.Recipe

class RecipeInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityRecipeInfoBinding.inflate(LayoutInflater.from(this))

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data: Recipe? = intent.getParcelableExtra("recipe")

        data?.let {
            binding.toolbar.title = it.label

            Glide.with(this)
                .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_find_recipes))
                .load(it.image)
                .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
                .into(binding.image)
            binding.image.contentDescription = it.label
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
