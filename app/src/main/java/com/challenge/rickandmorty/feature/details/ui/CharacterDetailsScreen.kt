package com.challenge.rickandmorty.feature.details.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.challenge.rickandmorty.R
import com.challenge.rickandmorty.feature.details.ui.view.CharacterDetailContent
import com.challenge.rickandmorty.feature.details.viewmodel.state.CharacterDetailsUIState
import com.challenge.rickandmorty.feature.details.viewmodel.state.CharacterDetailsUIState.OnDataError
import com.challenge.rickandmorty.feature.details.viewmodel.state.CharacterDetailsUIState.OnDetailsReady
import com.challenge.rickandmorty.feature.details.viewmodel.state.CharacterDetailsUIState.OnLoading
import com.country.styles.component.error.ErrorScreen
import com.country.styles.component.loading.LoadingScreen
import com.country.styles.component.topbar.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    navHostController: NavHostController,
    uiState: State<CharacterDetailsUIState>,
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navHostController,
                title = stringResource(R.string.details_title),
            )
        },
    ) { innerPadding ->

        when (val response = uiState.value) {
            is OnDetailsReady ->
                CharacterDetailContent(
                    list = response.data,
                    modifier = Modifier.padding(innerPadding),
                )

            is OnDataError -> ErrorScreen(errorMessage = response.error)
            is OnLoading -> LoadingScreen()
        }
    }
}
