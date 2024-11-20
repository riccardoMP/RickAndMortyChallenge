package com.challenge.rickandmorty.feature.details.domain.usecase

import com.challenge.rickandmorty.feature.details.domain.usecase.state.CharacterDetailsStateDomain
import kotlinx.coroutines.flow.Flow

interface CharacterDetailsUseCase {
    suspend fun loadData(id: Int): Flow<CharacterDetailsStateDomain>
}
