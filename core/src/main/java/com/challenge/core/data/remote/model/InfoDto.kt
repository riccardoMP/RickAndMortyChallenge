package com.challenge.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


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