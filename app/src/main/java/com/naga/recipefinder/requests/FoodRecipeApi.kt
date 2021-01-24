package com.naga.recipefinder.requests

import io.reactivex.Flowable
import retrofit2.http.GET
import com.naga.recipefinder.models.RecipeSearchResult

interface FoodRecipeApi {

    @GET("search?q=Burger&app_id=62b14e23&app_key=5ba8eeb7e0aa7d6d77de2f7823f5e50f")
    fun searchRecipe(): Flowable<RecipeSearchResult>

}
