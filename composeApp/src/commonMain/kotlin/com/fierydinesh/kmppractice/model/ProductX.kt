package com.fierydinesh.kmppractice.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductX(
    @SerialName("id")
    val id: Int?,
    @SerialName("title")
    val title: String?,
    @SerialName("price")
    val price: Double?,
    @SerialName("description")
    val description: String?,
    @SerialName("category")
    val category: String?,
    @SerialName("image")
    val image: String?,
    @SerialName("rating")
    val rating: Rating?
)