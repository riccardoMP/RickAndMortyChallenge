package com.challenge.rickandmorty.feature.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.challenge.rickandmorty.feature.details.domain.usecase.CharacterDetailsUseCase
import com.challenge.rickandmorty.feature.details.domain.usecase.state.CharacterDetailsStateDomain
import com.challenge.rickandmorty.feature.details.domain.usecase.state.CharacterDetailsStateDomain.DataError
import com.challenge.rickandmorty.feature.details.domain.usecase.state.CharacterDetailsStateDomain.DetailsReady
import com.challenge.rickandmorty.feature.details.domain.usecase.state.CharacterDetailsStateDomain.Loading
import com.challenge.rickandmorty.feature.details.viewmodel.state.CharacterDetailsUIState
import com.challenge.rickandmorty.feature.details.viewmodel.state.CharacterDetailsUIState.OnDataError
import com.challenge.rickandmorty.feature.details.viewmodel.state.CharacterDetailsUIState.OnDetailsReady
import com.challenge.rickandmorty.feature.details.viewmodel.state.CharacterDetailsUIState.OnLoading
import com.challenge.rickandmorty.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: CharacterDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterDetailsUIState>(OnLoading)

    val uiState: StateFlow<CharacterDetailsUIState> = _uiState
        .flatMapLatest {
            val id: Int =
                savedStateHandle.get<Int>(Screen.CHARACTER_ID) ?: return@flatMapLatest flowOf(
                    OnDataError("Character ID not found")
                )
            useCase.loadData(id).map { result ->

                when (result) {
                    is Loading -> OnLoading
                    is DetailsReady -> OnDetailsReady(result.data)

                    is DataError -> OnDataError(result.error)
                }

            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), OnLoading)
}
