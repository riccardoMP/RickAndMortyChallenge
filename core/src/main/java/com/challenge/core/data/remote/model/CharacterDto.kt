package com.challenge.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CharacterDto(
    @SerialName("info")
    val info: InfoDto,
    @SerialName("results")
    val results: List<CharacterDetailDto>,
)
