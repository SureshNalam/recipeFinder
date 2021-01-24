package com.naga.recipefinder.models

data class RecipeSearchResult(
    val count: Int,
    val from: Int,
    val hits: List<Hit>,
    val more: Boolean,
    val q: String,
    val to: Int
)