package com.challenge.rickandmorty.feature.details.viewmodel.state

import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData

sealed class CharacterDetailsUIState {
    data class OnDetailsReady(val data: List<CharacterDetailData>) : CharacterDetailsUIState()
    data class OnDataError(val error: String) : CharacterDetailsUIState()
    data object OnLoading : CharacterDetailsUIState()
}
