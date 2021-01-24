package com.naga.recipefinder.repositories

import androidx.lifecycle.MutableLiveData
import io.mockk.every
import io.mockk.mockk
import io.reactivex.disposables.CompositeDisposable
import org.junit.Test
import com.naga.recipefinder.models.Hit
import com.naga.recipefinder.requests.FoodRecipeApiClient

class FoodRepositoryTest {

    private val foodRecipeApiClient: FoodRecipeApiClient = mockk()

    private val classUnderTest = FoodRepository(foodRecipeApiClient)



    @Test
    fun recipesLoading() {
        every { foodRecipeApiClient.recipesLoading } returns MutableLiveData(true)

        assert(classUnderTest.recipesLoading().value!!)
    }


    @Test
    fun recipes() {
        every { foodRecipeApiClient.recipes } returns RECIPE_LIST

        assert(classUnderTest.recipes() == RECIPE_LIST)
    }

    @Test
    fun searchApi() {
        every { classUnderTest.searchApi(QUERY) } returns DISPOSABLE

        assert(classUnderTest.searchApi(QUERY) == DISPOSABLE)
    }

    companion object {
        private const val QUERY = "query"
        private val RECIPE_LIST = MutableLiveData(listOf(Hit(false, false, recipe)))
        private val DISPOSABLE = CompositeDisposable()
    }
}
