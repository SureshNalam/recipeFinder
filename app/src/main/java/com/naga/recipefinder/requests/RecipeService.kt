package com.naga.recipefinder.requests

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.naga.recipefinder.utils.Constants


class RecipeService {

    companion object {
        private val retrofitBuilder =
            Retrofit.Builder()
                .baseUrl(Constants.BASE_RECIPE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        private val retrofit = retrofitBuilder.build()

        val FOOD_RECIPE_API: FoodRecipeApi = retrofit.create(FoodRecipeApi::class.java)
    }
}
