package com.challenge.core.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.challenge.core.data.local.LocalDatabase
import com.challenge.core.data.local.model.CharacterEntity
import com.challenge.core.data.local.model.RemoteKeyEntity
import com.challenge.core.data.remote.mapper.toCharacter
import com.challenge.core.data.remote.model.CharacterDto
import com.challenge.core.util.getPageIntFromUrl
import javax.inject.Inject

@ExperimentalPagingApi
internal class CharacterRemoteMediator @Inject constructor(
    private val localDatabase: LocalDatabase,
    private val apiService: ApiService,
    private val name: String? = null,
) : RemoteMediator<Int, CharacterEntity>() {

    private val remoteKeyId = "rick_and_morty"

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>,
    ): MediatorResult {
        return try {
            val pageNumber: Int = getPageNumber(loadType) ?: return MediatorResult.Success(
                endOfPaginationReached = true,
            )

            val characterDto: CharacterDto =
                apiService.getCharacters(page = pageNumber, name = name)
            val characterEntityList: List<CharacterEntity> = characterDto.results.map { resultDto ->
                resultDto.toCharacter()
            }
            val nextPage: Int? = extractNextPage(characterDto)

            saveResultsToDatabase(
                loadType = loadType,
                characterEntityList = characterEntityList,
                nextPage = nextPage,
            )
            MediatorResult.Success(endOfPaginationReached = characterDto.info.count < state.config.pageSize)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getPageNumber(loadType: LoadType): Int? {
        return when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> null
            LoadType.APPEND -> {
                val remoteKey: RemoteKeyEntity? = localDatabase.remoteKeyDao.getById(remoteKeyId)
                if (remoteKey == null || remoteKey.nextPage == 0) {
                    // END OF PAGINATION REACHED
                    return null
                }
                remoteKey.nextPage
            }
        }
    }

    fun extractNextPage(characterDto: CharacterDto): Int? {
        return characterDto.info.next?.let { getPageIntFromUrl(it) }
    }

    private suspend fun saveResultsToDatabase(
        loadType: LoadType,
        characterEntityList: List<CharacterEntity>,
        nextPage: Int?,
    ) = with(localDatabase) {
        withTransaction {
            if (loadType == LoadType.REFRESH) {
                characterDao.clearAll()
                remoteKeyDao.deleteById(remoteKeyId)
            }
            characterDao.insertAll(characterEntityList)
            remoteKeyDao.insert(
                RemoteKeyEntity(
                    id = remoteKeyId,
                    nextPage = nextPage ?: 0,
                ),
            )
        }
    }
}
