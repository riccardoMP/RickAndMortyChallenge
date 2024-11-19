package com.challenge.rickandmorty.feature.character.domain.mapper

import androidx.compose.ui.graphics.Color
import com.challenge.core.data.local.model.CharacterEntity
import com.challenge.rickandmorty.R
import com.challenge.rickandmorty.feature.character.domain.model.CharacterData
import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData
import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData.RowInformation


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

fun CharacterEntity.toCharacterDetailData(): CharacterDetailData {
    val color = when (status) {
        "Alive" -> Color.Green
        "Dead" -> Color.Red
        else -> Color.Gray
    }

    return CharacterDetailData(
        name = RowInformation(R.string.details_name, name),
        species = RowInformation(R.string.details_species, species),
        gender = RowInformation(R.string.details_gender, gender),
        location = RowInformation(R.string.details_location, location),
        status = RowInformation(R.string.details_status, status),
        imageUrl = imageUrl,
    )
}