package com.challenge.rickandmorty.feature.details.domain.model

import androidx.annotation.StringRes

sealed class CharacterDetailData {
    data class Title(@StringRes val title: Int) : CharacterDetailData()
    data class ImageUrl(val imageUrl: String) : CharacterDetailData()
    data class RowInformation(@StringRes val title: Int, val value: String) : CharacterDetailData()
    data object Divider : CharacterDetailData()
}
