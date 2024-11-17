package com.challenge.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class CharacterDto(
    @SerialName("info")
    val info: InfoDto,
    @SerialName("results")
    val results: List<ResultDto>
) {
    @Serializable
    data class InfoDto(
        @SerialName("count")
        val count: Int,
        @SerialName("next")
        val next: String?,
        @SerialName("pages")
        val pages: Int,
        @SerialName("prev")
        val prev: String?
    )

    @Serializable
    data class ResultDto(
        @SerialName("created")
        val created: String,
        @SerialName("episode")
        val episode: List<String>,
        @SerialName("gender")
        val gender: String,
        @SerialName("id")
        val id: Int,
        @SerialName("image")
        val image: String,
        @SerialName("name")
        val name: String,
        @SerialName("species")
        val species: String,
        @SerialName("status")
        val status: String,
        @SerialName("type")
        val type: String,
        @SerialName("url")
        val url: String
    )
}
