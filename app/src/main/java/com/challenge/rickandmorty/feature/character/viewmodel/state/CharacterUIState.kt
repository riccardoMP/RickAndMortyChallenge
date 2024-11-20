package com.challenge.rickandmorty.feature.character.viewmodel.state

import androidx.paging.PagingData
import com.challenge.rickandmorty.feature.character.domain.model.CharacterData
import kotlinx.coroutines.flow.Flow

sealed class CharacterUIState {
    data class OnDataReady(val dataList: Flow<PagingData<CharacterData>>) : CharacterUIState()
    data class OnDataError(val error: String) : CharacterUIState()
    data object OnLoading : CharacterUIState()
}
