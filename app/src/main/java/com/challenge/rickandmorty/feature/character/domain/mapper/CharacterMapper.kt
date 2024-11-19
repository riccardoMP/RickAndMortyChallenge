package com.challenge.rickandmorty.feature.character.domain.mapper

import androidx.compose.ui.graphics.Color
import com.challenge.core.data.local.model.CharacterEntity
import com.challenge.rickandmorty.R
import com.challenge.rickandmorty.feature.character.domain.model.CharacterData
import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData
import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData.Divider
import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData.ImageUrl
import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData.RowInformation
import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData.Title


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

fun CharacterEntity.toCharacterDetailData(): List<CharacterDetailData> {
    return listOf(
        ImageUrl(imageUrl),
        Title(R.string.details_information),
        RowInformation(R.string.details_status, status),
        Divider,
        RowInformation(R.string.details_name, name),
        Divider,
        RowInformation(R.string.details_species, species),
        Divider,
        RowInformation(R.string.details_gender, gender),
        Title(R.string.details_location),
        RowInformation(R.string.details_location, location),
        Title(R.string.details_origin),
        RowInformation(R.string.details_origin, origin)
    )
}