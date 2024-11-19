package com.challenge.core.data.repository

import androidx.paging.PagingData
import com.challenge.core.data.local.model.CharacterEntity
import kotlinx.coroutines.flow.Flow


interface CharacterRepository {
    suspend fun getAllCharacters(): Flow<PagingData<CharacterEntity>>
    suspend fun getACharacterDetails(id: Int): CharacterEntity?
}