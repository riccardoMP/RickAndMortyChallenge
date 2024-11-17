package com.challenge.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.challenge.core.data.local.LocalDatabase
import com.challenge.core.data.local.model.CharacterEntity
import com.challenge.core.data.remote.ApiService
import com.challenge.core.data.remote.CharacterRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ExperimentalPagingApi
internal class CharacterRepositoryImpl @Inject constructor(
    private val database: LocalDatabase,
    private val apiService: ApiService,
) : CharacterRepository {

    override suspend fun getAllCharacters(): Flow<PagingData<CharacterEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = CharacterRemoteMediator(
                localDatabase = database,
                apiService = apiService,
            ),
            pagingSourceFactory = {
                database.characterDao.pagingSource()
            },
        ).flow
    }
}