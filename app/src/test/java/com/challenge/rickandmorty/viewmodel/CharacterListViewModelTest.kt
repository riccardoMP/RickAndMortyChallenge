package com.challenge.rickandmorty.viewmodel

import androidx.paging.PagingData
import com.challenge.rickandmorty.feature.character.domain.model.CharacterData
import com.challenge.rickandmorty.feature.character.domain.usecase.CharacterUseCase
import com.challenge.rickandmorty.feature.character.domain.usecase.state.CharacterStateDomain.DataReady
import com.challenge.rickandmorty.feature.character.domain.usecase.state.CharacterStateDomain.Loading
import com.challenge.rickandmorty.feature.character.viewmodel.CharacterListViewModel
import com.challenge.rickandmorty.feature.character.viewmodel.state.CharacterUIState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterListViewModelTest {

    @Mock
    private lateinit var useCase: CharacterUseCase

    private lateinit var viewModel: CharacterListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = CharacterListViewModel(useCase)
    }

    @Test
    fun `when loadData returns Loading, uiState should be OnLoading`() = runTest {
        `when`(useCase.loadData()).thenReturn(flow { emit(Loading) })

        val states = mutableListOf<CharacterUIState>()
        val job = launch {
            viewModel.uiState.collect { state ->
                states.add(state)
            }
        }

        advanceTimeBy(100)
        job.cancel()

        assertEquals(CharacterUIState.OnLoading, states.last())
    }

    private fun createDataReadyInstance(): DataReady {
        val characterList = listOf(
            mock(CharacterData::class.java),
            mock(CharacterData::class.java),
        )

        val pagingData = PagingData.from(characterList)

        val flow: Flow<PagingData<CharacterData>> = flow {
            emit(pagingData)
        }

        return DataReady(flow)
    }
}
