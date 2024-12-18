package com.challenge.core.data.remote.mapper

import com.challenge.core.data.local.model.CharacterEntity
import com.challenge.core.data.remote.model.CharacterDetailDto

internal fun CharacterDetailDto.toCharacter(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        imageUrl = image,
        gender = gender,
        status = status,
        species = species,
        location = location.name,
        origin = origin.name,
        type = type,
    )
}
