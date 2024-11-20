package com.challenge.core.repository

import androidx.paging.ExperimentalPagingApi
import com.challenge.core.data.local.LocalDatabase
import com.challenge.core.data.local.dao.CharacterDao
import com.challenge.core.data.local.dao.RemoteKeyDao
import com.challenge.core.data.local.model.CharacterEntity
import com.challenge.core.data.remote.ApiService
import com.challenge.core.data.remote.model.CharacterDetailDto
import com.challenge.core.data.repository.CharacterRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalPagingApi
class CharacterRepositoryImplTest {

    private lateinit var characterRepository: CharacterRepositoryImpl

    private lateinit var localDatabase: LocalDatabase

    @Mock
    private lateinit var characterDaoMock: CharacterDao

    @Mock
    private lateinit var remoteKeyDaoMock: RemoteKeyDao

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        localDatabase = mock(LocalDatabase::class.java).apply {
            `when`(this.characterDao).thenReturn(characterDaoMock)
            `when`(this.remoteKeyDao).thenReturn(remoteKeyDaoMock)
        }

        characterRepository =
            CharacterRepositoryImpl(database = localDatabase, apiService = apiService)
    }

    @Test
    fun `Given an id, When getACharacterDetails is called, Then it should return the CharacterEntity from Database`() =
        runTest {
            val id = 1
            val expectedCharacter = CharacterEntity(
                id = id,
                gender = "Male",
                imageUrl = "http://example.com/image.jpg",
                location = "New York",
                name = "Spider-Man",
                origin = "Earth",
                status = "Alive",
                species = "Human",
                type = "Superhero",
            )
            `when`(characterDaoMock.getById(id)).thenReturn(expectedCharacter)

            val result = characterRepository.getACharacterDetails(id)

            assertEquals(expectedCharacter, result)
            verify(characterDaoMock, never()).insert(expectedCharacter)
        }

    @Test
    fun `Given an id, When getACharacterDetails is called, Then it should return the CharacterEntity from API if not found in Database`() =
        runTest {
            val id = 1
            val expectedCharacter = CharacterEntity(
                id = id,
                gender = "Male",
                imageUrl = "url",
                location = "New York",
                name = "Spider-Man",
                origin = "Earth",
                status = "Alive",
                species = "Human",
                type = "Superhero",
            )

            val dto = CharacterDetailDto(
                id = id,
                gender = "Male",
                url = "http://example.com/image.jpg",
                location = CharacterDetailDto.LocationDto("New York", "url"),
                name = "Spider-Man",
                origin = CharacterDetailDto.OriginDto(name = "Earth", "url"),
                status = "Alive",
                species = "Human",
                type = "Superhero",
                created = "",
                image = "url",
            )
            `when`(characterDaoMock.getById(id)).thenReturn(null)
            `when`(apiService.getCharacterDetails(id)).thenReturn(dto)

            val character = characterRepository.getACharacterDetails(id)

            assertEquals(expectedCharacter, character)
            verify(characterDaoMock).insert(expectedCharacter)
        }
}
