package com.naga.recipefinder.models

data class Hit(
    val bookmarked: Boolean,
    val bought: Boolean,
    val recipe: Recipe
)