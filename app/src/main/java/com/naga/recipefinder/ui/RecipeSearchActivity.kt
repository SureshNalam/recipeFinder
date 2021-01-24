package com.naga.recipefinder.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.naga.recipefinder.R
import com.naga.recipefinder.databinding.ActivityRecipeSearchBinding

class RecipeSearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var recipeSearchViewModel: RecipeSearchViewModel
    private lateinit var binding: ActivityRecipeSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding = ActivityRecipeSearchBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentSearchContainer, RecipeSearchFragment.newInstance())
                .commitNow()

        binding.searchView.setOnQueryTextListener(this)
        recipeSearchViewModel = ViewModelProvider(this).get(RecipeSearchViewModel::class.java)
    }

    override fun onPause() {
        super.onPause()
        recipeSearchViewModel.cancel()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        binding.searchView.clearFocus()
        recipeSearchViewModel.apply {
            setSearchTerm(query.orEmpty())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}
