package com.challenge.rickandmorty.feature.details.domain.usecase.state

import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData


sealed interface CharacterDetailsStateDomain {
    data class DetailsReady(val data: CharacterDetailData) : CharacterDetailsStateDomain
    data class DataError(val error: String) : CharacterDetailsStateDomain
    data object Loading : CharacterDetailsStateDomain
}