package com.challenge.rickandmorty.feature.character.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.challenge.rickandmorty.R
import com.challenge.rickandmorty.feature.character.ui.view.CharacterListContent
import com.challenge.rickandmorty.feature.character.viewmodel.state.CharacterUIState
import com.challenge.rickandmorty.feature.character.viewmodel.state.CharacterUIState.OnDataError
import com.challenge.rickandmorty.feature.character.viewmodel.state.CharacterUIState.OnDataReady
import com.challenge.rickandmorty.feature.character.viewmodel.state.CharacterUIState.OnLoading
import com.challenge.rickandmorty.util.Screen
import com.country.styles.component.error.ErrorScreen
import com.country.styles.component.loading.LoadingScreen
import com.country.styles.component.topbar.CustomTopAppBar

@Composable
@ExperimentalMaterial3Api
fun CharacterListScreen(
    navHostController: NavHostController,
    uiState: State<CharacterUIState>,
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navHostController,
                title = stringResource(R.string.characters_title),
                actions = {
                    IconButton(onClick = { /* Handle search */ }) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                }
            )
        }
    ) { innerPadding ->

        val onCharacterClick: (Int) -> Unit = { id ->
            val route: String = Screen.DetailsScreen.passId(id = id)
            navHostController.navigate(route)
        }

        when (val response = uiState.value) {
            is OnDataReady -> {
                CharacterListContent(
                    characterPagingItems = response.dataList.collectAsLazyPagingItems(),
                    onCharacterClick = onCharacterClick,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            is OnDataError -> ErrorScreen(errorMessage = response.error)
            is OnLoading -> LoadingScreen()
        }
    }
}


