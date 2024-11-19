package com.challenge.core.remote

import com.challenge.core.data.remote.ApiService
import com.challenge.core.data.remote.model.CharacterDetailDto
import com.challenge.core.data.remote.model.CharacterDto
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.anyString
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ApiServiceTest {

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `Given an apiService, When getCharacters is called, Then it should return a CharacterDto`() {
        runBlocking {
            val dto = mock(CharacterDto::class.java)

            doReturn(dto).`when`(apiService).getCharacters(page = anyInt(), name = anyString())

            val result = apiService.getCharacters(page = anyInt(), name = anyString())

            assertEquals(dto, result)
            verify(apiService).getCharacters(page = anyInt(), name = anyString())
        }
    }

    @Test
    fun `Given an apiService, When getCharacterDetails is called, Then it should return a CharacterDetailDto`() {
        runBlocking {
            val dto = mock(CharacterDetailDto::class.java)

            doReturn(dto).`when`(apiService).getCharacterDetails(characterId = anyInt())

            val result = apiService.getCharacterDetails(characterId = anyInt())

            assertEquals(dto, result)
            verify(apiService).getCharacterDetails(characterId = anyInt())
        }
    }
}