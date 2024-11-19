package com.challenge.rickandmorty.feature.details.domain.model

import androidx.annotation.StringRes
import com.challenge.rickandmorty.R

data class CharacterDetailData(
    val name: RowInformation,
    val species: RowInformation,
    val gender: RowInformation,
    val location: RowInformation,
    val imageUrl: String,
    val status: RowInformation,
) {
    data class RowInformation(@StringRes val title: Int, val value: String)

    companion object {
        fun init() = CharacterDetailData(
            name = RowInformation(title = R.string.details_title, value = "Rick Sanchez"),
            species = RowInformation(title = R.string.details_species, value = "Human"),
            gender = RowInformation(title = R.string.details_gender, value = "Male"),
            location = RowInformation(title = R.string.details_location, value = "Earth"),
            status = RowInformation(title = R.string.details_status, value = "Alive"),
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        )
    }
}