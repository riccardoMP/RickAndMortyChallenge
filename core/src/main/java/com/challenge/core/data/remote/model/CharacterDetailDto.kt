package com.challenge.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CharacterDetailDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("created")
    val created: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("image")
    val image: String,
    @SerialName("location")
    val location: LocationDto,
    @SerialName("origin")
    val origin: OriginDto,
    @SerialName("species")
    val species: String,
    @SerialName("status")
    val status: String,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String,
) {
    @Serializable
    data class LocationDto(
        @SerialName("name")
        val name: String,
        @SerialName("url")
        val url: String,
    )

    @Serializable
    data class OriginDto(
        @SerialName("name")
        val name: String,
        @SerialName("url")
        val url: String,
    )
}
