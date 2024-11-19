package com.challenge.core

import com.challenge.core.data.remote.ApiService
import com.challenge.core.data.remote.model.CharacterDto
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.anyInt
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
    fun `Should return expected CharacterDto response`() {
        runBlocking {
            val dto = mock(CharacterDto::class.java)

            doReturn(dto).`when`(apiService).getCharacters(page = anyInt())

            val result = apiService.getCharacters(page = anyInt())

            assertEquals(dto, result)
            verify(apiService).getCharacters(page = anyInt())
        }
    }
}