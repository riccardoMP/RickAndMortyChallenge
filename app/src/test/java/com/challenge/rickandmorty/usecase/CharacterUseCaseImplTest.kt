package com.challenge.rickandmorty.usecase

import androidx.paging.PagingData
import com.challenge.core.data.local.model.CharacterEntity
import com.challenge.core.data.repository.CharacterRepository
import com.challenge.rickandmorty.feature.character.domain.usecase.CharacterUseCaseImpl
import com.challenge.rickandmorty.feature.character.domain.usecase.state.CharacterStateDomain
import com.challenge.rickandmorty.feature.character.domain.usecase.state.CharacterStateDomain.DataReady
import com.challenge.rickandmorty.feature.character.domain.usecase.state.CharacterStateDomain.Loading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CharacterUseCaseImplTest {

    private lateinit var characterUseCase: CharacterUseCaseImpl

    @Mock
    private lateinit var mockRepository: CharacterRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        characterUseCase = CharacterUseCaseImpl(mockRepository)
    }

    @Test
    fun `Given an empty name, When loadData is called, Then it should emits DataReady`() = runTest {
        val name = ""

        val flow: Flow<CharacterStateDomain> = characterUseCase.loadData(name)

        flow.collect { state ->
            assertTrue(state is DataReady)
        }
    }

    @Test
    fun `Given a name, When loadData is called, Then it should emits Loading and DataReady`() =
        runTest {
            val name = "Rick"
            val characterDataList = listOf(
                CharacterEntity(
                    id = 1,
                    gender = "Male",
                    imageUrl = "http://example.com/image.jpg",
                    location = "New York",
                    name = name,
                    origin = "Earth",
                    status = "Alive",
                    species = "Human",
                    type = "Superhero",
                ),
            )

            val pagingData = PagingData.from(characterDataList)
            `when`(mockRepository.getAllCharacters(name)).thenReturn(flowOf(pagingData))

            val flow: Flow<CharacterStateDomain> = characterUseCase.loadData(name)

            flow.collect { state ->
                when (state) {
                    is Loading -> {
                        assertEquals(state, Loading)
                    }

                    is DataReady -> {
                        assertEquals(state, DataReady(state.dataList))
                    }

                    else -> {
                        assertTrue(false)
                    }
                }
            }
        }
}
