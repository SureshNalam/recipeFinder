package com.naga.recipefinder.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import com.naga.recipefinder.repositories.FoodRepository

class RecipeSearchViewModel : ViewModel() {

    private val _repository = FoodRepository.instance

    fun recipesLoading() = _repository.recipesLoading()

    fun recipes() = _repository.recipes()

    private val disposables = mutableListOf<Disposable>()

    fun setSearchTerm(searchTerm: String) {
        disposables.add(_repository.searchApi(searchTerm))
    }

    fun cancel() {
        disposables.forEach {
            it.dispose()
        }
    }
}
