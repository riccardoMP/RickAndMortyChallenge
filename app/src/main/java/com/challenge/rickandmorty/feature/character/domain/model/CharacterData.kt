package com.challenge.rickandmorty.feature.character.domain.model

import androidx.compose.ui.graphics.Color

data class CharacterData(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val species: String,
    val status: String,
    val colorStatus: Color,
)