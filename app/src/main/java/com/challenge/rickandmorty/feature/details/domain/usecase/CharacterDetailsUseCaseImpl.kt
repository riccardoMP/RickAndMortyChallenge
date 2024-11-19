package com.challenge.rickandmorty.feature.details.domain.usecase

import com.challenge.core.data.local.model.CharacterEntity
import com.challenge.core.data.repository.CharacterRepository
import com.challenge.rickandmorty.feature.character.domain.mapper.toCharacterDetailData
import com.challenge.rickandmorty.feature.details.domain.usecase.state.CharacterDetailsStateDomain
import com.challenge.rickandmorty.feature.details.domain.usecase.state.CharacterDetailsStateDomain.DataError
import com.challenge.rickandmorty.feature.details.domain.usecase.state.CharacterDetailsStateDomain.DetailsReady
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


internal class CharacterDetailsUseCaseImpl @Inject constructor(
    private val repository: CharacterRepository,
) : CharacterDetailsUseCase {

    override suspend fun loadData(id: Int): Flow<CharacterDetailsStateDomain> = flow {
        emit(CharacterDetailsStateDomain.Loading)

        val data: CharacterEntity? = repository.getACharacterDetails(id)

        if (data != null) {
            val characterDetailData = data.toCharacterDetailData()
            emit(DetailsReady(characterDetailData))
        } else {
            emit(DataError("Error"))
        }

    }.flowOn(Dispatchers.IO)
}