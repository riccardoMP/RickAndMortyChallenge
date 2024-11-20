package com.challenge.rickandmorty.feature.character.domain.usecase

import com.challenge.rickandmorty.feature.character.domain.usecase.state.CharacterStateDomain
import kotlinx.coroutines.flow.Flow

interface CharacterUseCase {
    suspend fun loadData(name: String? = null): Flow<CharacterStateDomain>
}
