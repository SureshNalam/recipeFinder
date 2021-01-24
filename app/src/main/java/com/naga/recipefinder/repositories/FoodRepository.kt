package com.naga.recipefinder.repositories

import io.reactivex.disposables.Disposable
import com.naga.recipefinder.requests.FoodRecipeApiClient

class FoodRepository(private val apiClient: FoodRecipeApiClient) {

    companion object {
        val instance = FoodRepository(FoodRecipeApiClient.instance)
    }

    fun recipesLoading() = apiClient.recipesLoading

    fun recipes() = apiClient.recipes

    fun searchApi(query: String): Disposable {
        return apiClient.searchApi(query)
    }
}
