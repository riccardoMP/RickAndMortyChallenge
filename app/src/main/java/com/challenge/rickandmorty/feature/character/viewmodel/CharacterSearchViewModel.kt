package com.challenge.rickandmorty.feature.character.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.challenge.rickandmorty.feature.character.domain.usecase.CharacterUseCase
import com.challenge.rickandmorty.feature.character.domain.usecase.state.CharacterStateDomain.DataError
import com.challenge.rickandmorty.feature.character.domain.usecase.state.CharacterStateDomain.DataReady
import com.challenge.rickandmorty.feature.character.domain.usecase.state.CharacterStateDomain.Loading
import com.challenge.rickandmorty.feature.character.viewmodel.state.CharacterUIState
import com.challenge.rickandmorty.feature.character.viewmodel.state.CharacterUIState.OnDataError
import com.challenge.rickandmorty.feature.character.viewmodel.state.CharacterUIState.OnDataReady
import com.challenge.rickandmorty.feature.character.viewmodel.state.CharacterUIState.OnLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CharacterSearchViewModel @Inject constructor(
    private val useCase: CharacterUseCase
) : ViewModel() {

    private val searchQueryStateFlow = MutableStateFlow("")
    val searchQuery: StateFlow<String> = searchQueryStateFlow

    private var searchJob: Job? = null


    val uiState: StateFlow<CharacterUIState> = searchQueryStateFlow
        .flatMapLatest { query ->
            useCase.loadData(query).map { result ->
                when (result) {
                    is Loading -> OnLoading
                    is DataReady -> OnDataReady(dataList = result.dataList.cachedIn(viewModelScope))
                    is DataError -> OnDataError(result.error)
                }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), OnLoading)

    fun updateSearchQuery(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            searchQueryStateFlow.value = query
        }
    }
}