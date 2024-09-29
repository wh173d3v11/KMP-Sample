package com.fierydinesh.kmppractice.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    @SerialName("rate")
    val rate: Double?,
    @SerialName("count")
    val count: Int?
)