package com.neo.testexoplayer


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class Post(
    @SerializedName("body")
    val text: String,
    val id: Int,
    val title: String,
    val userId: Int
)