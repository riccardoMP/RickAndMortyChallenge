package com.challenge.rickandmorty.feature.character.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.challenge.core.data.repository.CharacterRepository
import com.challenge.rickandmorty.feature.character.domain.mapper.toCharacterData
import com.challenge.rickandmorty.feature.character.domain.model.CharacterData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class CharacterUseCaseImpl @Inject constructor(
    private val repository: CharacterRepository,
) : CharacterUseCase {
    override suspend fun loadData(): Flow<PagingData<CharacterData>> {
        return repository.getAllCharacters().map { pagingData ->
            pagingData.map {
                it.toCharacterData()
            }
        }.flowOn(Dispatchers.IO)
    }


}