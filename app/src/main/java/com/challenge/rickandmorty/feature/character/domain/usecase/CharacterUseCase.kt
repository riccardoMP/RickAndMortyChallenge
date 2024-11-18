package com.challenge.rickandmorty.feature.character.domain.usecase

import androidx.paging.PagingData
import com.challenge.rickandmorty.feature.character.domain.model.CharacterData
import com.challenge.rickandmorty.feature.character.domain.usecase.state.CharacterStateDomain
import kotlinx.coroutines.flow.Flow

interface CharacterUseCase {
    suspend fun loadData(): Flow<PagingData<CharacterData>>
    //suspend fun loadData(): Flow<CharacterStateDomain>
}