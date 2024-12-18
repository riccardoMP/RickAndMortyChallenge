package com.challenge.rickandmorty.feature.character.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.challenge.core.data.repository.CharacterRepository
import com.challenge.rickandmorty.feature.character.domain.mapper.toCharacterData
import com.challenge.rickandmorty.feature.character.domain.usecase.state.CharacterStateDomain
import com.challenge.rickandmorty.feature.character.domain.usecase.state.CharacterStateDomain.DataError
import com.challenge.rickandmorty.feature.character.domain.usecase.state.CharacterStateDomain.DataReady
import com.challenge.rickandmorty.feature.character.domain.usecase.state.CharacterStateDomain.Loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class CharacterUseCaseImpl @Inject constructor(
    private val repository: CharacterRepository,
) : CharacterUseCase {

    override suspend fun loadData(name: String?): Flow<CharacterStateDomain> = flow {
        if (name?.isEmpty() == true) {
            emit(DataReady(dataList = flowOf(PagingData.empty())))
            return@flow
        }

        emit(Loading)
        runCatching {
            repository.getAllCharacters(name = name)
                .map { pagingData ->
                    pagingData.map {
                        it.toCharacterData()
                    }
                }
        }.onSuccess { data ->
            emit(DataReady(data))
        }.onFailure { exception ->
            emit(DataError("Exception")) // Emit error state if fetching fails
        }

        /*val data: Flow<PagingData<CharacterData>> =
            repository.getAllCharacters(name = name).map { pagingData ->
                pagingData.map {
                    it.toCharacterData()
                }
            }
        emit(DataReady(data))*/
    }.flowOn(Dispatchers.IO)
}
