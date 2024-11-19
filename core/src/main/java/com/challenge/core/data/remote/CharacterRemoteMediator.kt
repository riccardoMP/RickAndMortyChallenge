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
            val pageNumber: Int = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    // RETRIEVE NEXT OFFSET FROM DATABASE
                    val remoteKey: RemoteKeyEntity? =
                        localDatabase.remoteKeyDao.getById(remoteKeyId)
                    if (remoteKey == null || remoteKey.nextPage == 0) // END OF PAGINATION REACHED
                        return MediatorResult.Success(endOfPaginationReached = true)
                    remoteKey.nextPage
                }
            }
            // MAKE API CALL
            val characterDto : CharacterDto = apiService.getCharacters(page = pageNumber, name = name)
            val characterEntityList: List<CharacterEntity> = characterDto.results.map { resultDto ->
                resultDto.toCharacter()
            }

            var nextPage: Int? = null
            if (characterDto.info.next != null) {
                nextPage = getPageIntFromUrl(characterDto.info.next)
            }

            // SAVE RESULTS AND NEXT OFFSET TO DATABASE
            localDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    // IF REFRESHING, CLEAR DATABASE FIRST
                    localDatabase.characterDao.clearAll()
                    localDatabase.remoteKeyDao.deleteById(remoteKeyId)
                }
                localDatabase.characterDao.insertAll(characterEntityList)
                localDatabase.remoteKeyDao.insert(
                    RemoteKeyEntity(
                        id = remoteKeyId,
                        nextPage = nextPage ?: 0,
                    )
                )
            }
            // CHECK IF END OF PAGINATION REACHED
            MediatorResult.Success(endOfPaginationReached = characterDto.info.count < state.config.pageSize)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
