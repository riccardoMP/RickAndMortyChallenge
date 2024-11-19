package com.challenge.rickandmorty.feature.character.domain.usecase.state

import androidx.paging.PagingData
import com.challenge.rickandmorty.feature.character.domain.model.CharacterData
import kotlinx.coroutines.flow.Flow


sealed interface CharacterStateDomain {
    data class DataReady(val dataList: Flow<PagingData<CharacterData>>) : CharacterStateDomain
    data class DataError(val error: String) : CharacterStateDomain
    data object Loading : CharacterStateDomain
}