package com.challenge.core.remote

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.Room
import androidx.room.withTransaction
import com.challenge.core.data.local.LocalDatabase
import com.challenge.core.data.local.dao.CharacterDao
import com.challenge.core.data.local.dao.RemoteKeyDao
import com.challenge.core.data.local.model.CharacterEntity
import com.challenge.core.data.local.model.RemoteKeyEntity
import com.challenge.core.data.remote.ApiService
import com.challenge.core.data.remote.CharacterRemoteMediator
import com.challenge.core.data.remote.model.CharacterDto
import com.challenge.core.data.remote.model.InfoDto
import com.challenge.core.mock.charactersPage1json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalPagingApi
class CharacterRemoteMediatorTest {

    private val testJson = Json { ignoreUnknownKeys = true }

    @Mock
    private lateinit var localDatabase: LocalDatabase

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var characterRemoteMediator: CharacterRemoteMediator

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        localDatabase = Room.inMemoryDatabaseBuilder(
             mock(Context::class.java),
            LocalDatabase::class.java
        ).allowMainThreadQueries().build()

        // Mock the DAO methods
        val characterDao = mock(CharacterDao::class.java)
        val remoteKeyDao = mock(RemoteKeyDao::class.java)

        // Set up the LocalDatabase mock
        /*localDatabase = mock(LocalDatabase::class.java).apply {
            `when`(this.characterDao).thenReturn(characterDao)
            `when`(this.remoteKeyDao).thenReturn(remoteKeyDao)
        }*/





        characterRemoteMediator = CharacterRemoteMediator(localDatabase, apiService)
    }

    /*
    @Test
    fun `load - REFRESH success`() = runBlocking {
        val dto: CharacterDto =
            testJson.decodeFromString<CharacterDto>(charactersPage1json)

        val pagingState: PagingState<Int, CharacterEntity> = createPagingState()

        `when`(apiService.getCharacters(0, null)).thenReturn(dto)


        // When
        val result = characterRemoteMediator.load(LoadType.REFRESH, pagingState)

        // Then
        verify(localDatabase.characterDao).clearAll()
        verify(localDatabase.remoteKeyDao).deleteById("rick_and_morty")
        verify(localDatabase.characterDao).insertAll(anyList())
        verify(localDatabase.remoteKeyDao).insert(any(RemoteKeyEntity::class.java))
        assert(result is RemoteMediator.MediatorResult.Success)
    }

    private fun createPagingState(): PagingState<Int, CharacterEntity> {
        return PagingState(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 20),
            leadingPlaceholderCount = 0
        )
    }

    @Test
    fun `load - APPEND success`() = runBlocking {
        // Given
        val remoteKeyEntity = RemoteKeyEntity(id = "rick_and_morty", nextPage = 1)
        val characterDto = CharacterDto(
            info = Info(count = 100, next = "https://api.example.com/characters?page=2"),
            results = listOf(/* Mock character DTOs */)
        )
        `when`(localDatabase.remoteKeyDao.getById("rick_and_morty")).thenReturn(remoteKeyEntity)
        `when`(apiService.getCharacters(1, null)).thenReturn(characterDto)

        // When
        val result = characterRemoteMediator.load(LoadType.APPEND, mock(PagingState::class.java))

        // Then
        verify(localDatabase.characterDao).insertAll(anyList())
        verify(localDatabase.remoteKeyDao).insert(any(RemoteKeyEntity::class.java))
        assert(result is MediatorResult.Success)
    }

    @Test
    fun `load - ERROR returns MediatorResult.Error`() = runBlocking {
        // Given
        `when`(apiService.getCharacters(0, null)).thenThrow(RuntimeException("Network Error"))

        // When
        val result = characterRemoteMediator.load(LoadType.REFRESH, mock(PagingState::class.java))

        // Then
        assert(result is MediatorResult.Error)
    }

    */
}