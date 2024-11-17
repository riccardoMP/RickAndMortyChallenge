package com.challenge.rickandmorty.feature.character.domain.mapper

import androidx.compose.ui.graphics.Color
import com.challenge.core.data.local.model.CharacterEntity
import com.challenge.rickandmorty.feature.character.domain.model.CharacterData



fun CharacterEntity.toCharacterData(): CharacterData {
    val color = when (status) {
        "Alive" -> Color.Green
        "Dead" -> Color.Red
        else -> Color.Gray
    }

    return CharacterData(
        id = id,
        name = name,
        imageUrl = imageUrl,
        species = species,
        status = status,
        colorStatus = color
    )
}