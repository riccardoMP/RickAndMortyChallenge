package com.challenge.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.challenge.core.data.local.LocalDatabase
import com.challenge.core.data.local.model.CharacterEntity
import com.challenge.core.data.remote.ApiService
import com.challenge.core.data.remote.CharacterRemoteMediator
import com.challenge.core.data.remote.mapper.toCharacter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
internal class CharacterRepositoryImpl @Inject constructor(
    private val database: LocalDatabase,
    private val apiService: ApiService,
) : CharacterRepository {

    companion object {
        const val MAX_PAGE_SIZE = 10
    }

    override suspend fun getAllCharacters(name: String?): Flow<PagingData<CharacterEntity>> {
        return Pager(
            config = PagingConfig(pageSize = MAX_PAGE_SIZE),
            remoteMediator = CharacterRemoteMediator(
                localDatabase = database,
                apiService = apiService,
                name = name,
            ),
            pagingSourceFactory = {
                database.characterDao.pagingSource()
            },
        ).flow
    }

    override suspend fun getACharacterDetails(id: Int): CharacterEntity? {
        val characterEntity: CharacterEntity? = database.characterDao.getById(id)

        return characterEntity ?: getCharacterDetailsDto(id)
    }

    private suspend fun getCharacterDetailsDto(id: Int): CharacterEntity? {
        return try {
            val entity: CharacterEntity = apiService.getCharacterDetails(id).toCharacter()
            database.characterDao.insert(entity)
            entity
        } catch (exception: Exception) {
            null
        }
    }
}
