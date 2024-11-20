package com.challenge.rickandmorty.feature.main.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.challenge.rickandmorty.feature.character.ui.CharacterListScreen
import com.challenge.rickandmorty.feature.character.ui.CharacterSearchScreen
import com.challenge.rickandmorty.feature.character.viewmodel.CharacterListViewModel
import com.challenge.rickandmorty.feature.character.viewmodel.CharacterSearchViewModel
import com.challenge.rickandmorty.feature.details.ui.CharacterDetailsScreen
import com.challenge.rickandmorty.feature.details.viewmodel.CharacterDetailsViewModel
import com.challenge.rickandmorty.util.Screen

@Composable
@ExperimentalMaterial3Api
fun RickMortyNavHost() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.RickMortyScreen.route,
    ) {
        composable(route = Screen.RickMortyScreen.route) {
            val viewModel = hiltViewModel<CharacterListViewModel>()

            CharacterListScreen(
                navHostController = navController,
                uiState = viewModel.uiState.collectAsState(),
                lazyListState = viewModel.lazyListState,
            )
        }

        composable(
            route = Screen.DetailsScreen.route,
            arguments = listOf(navArgument(name = Screen.CHARACTER_ID) { type = NavType.IntType }),
        ) {
            val viewModel = hiltViewModel<CharacterDetailsViewModel>()

            CharacterDetailsScreen(
                navHostController = navController,
                uiState = viewModel.uiState.collectAsState(),
            )
        }

        composable(route = Screen.SearchScreen.route) {
            val viewModel = hiltViewModel<CharacterSearchViewModel>()

            CharacterSearchScreen(
                navHostController = navController,
                uiState = viewModel.uiState.collectAsState(),
                searchQuery = viewModel.searchQuery.collectAsState().value,
                onValueChange = viewModel::updateSearchQuery,
                navigateUp = navController::navigateUp,
            )
        }
    }
}
