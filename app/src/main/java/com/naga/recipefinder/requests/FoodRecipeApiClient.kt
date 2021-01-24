package com.naga.recipefinder.requests

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.naga.recipefinder.models.Hit

class FoodRecipeApiClient(private val foodRecipeApi: FoodRecipeApi) {

    companion object {
        val instance = FoodRecipeApiClient(RecipeService.FOOD_RECIPE_API)
        private const val TAG = "FoodRecipeApiClient"
    }

    val recipesLoading: MutableLiveData<Boolean> = MutableLiveData()

    val recipes: MutableLiveData<List<Hit>> = MutableLiveData()

    fun searchApi(query: String): Disposable {
        startLoading()

        val searchFlowable = foodRecipeApi.searchRecipe()

        return searchFlowable
            .map { it.hits }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateResults(it)
            }, {
                stopLoading()
                Log.w(TAG, "foodApi: ", it)
            }, {
                stopLoading()
            })
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun updateResults(it: List<Hit>?
    ) {
        recipes.postValue(it)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun startLoading() {
        recipesLoading.postValue(true)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun stopLoading() {
        recipesLoading.postValue(false)
    }
}
