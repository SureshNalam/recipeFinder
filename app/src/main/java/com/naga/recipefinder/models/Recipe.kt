package com.naga.recipefinder.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
        @SerializedName("calories")
        val calories: Double,
        @SerializedName("cautions")
        val cautions: List<String>,
        @SerializedName("dietLabels")
        val dietLabels: List<String>,
        @SerializedName("image")
        val image: String,
        @SerializedName("label")
        val label: String,
        @SerializedName("shareAs")
        val shareAs: String,
        @SerializedName("source")
        val source: String,
        @SerializedName("totalTime")
        val totalTime: Double,
        @SerializedName("totalWeight")
        val totalWeight: Double,
        @SerializedName("uri")
        val uri: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("yield")
        val yield: Double
) : Parcelable