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
import com.challenge.rickandmorty.feature.character.viewmodel.CharacterViewModel
import com.challenge.rickandmorty.feature.details.ui.CountryDetailsScreen
import com.challenge.rickandmorty.util.Screen

@Composable
@ExperimentalMaterial3Api
fun RickMortyNavHost() {

    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.RickMortyScreen.route
    ) {
        composable(route = Screen.RickMortyScreen.route) {
            val viewModel = hiltViewModel<CharacterViewModel>()

            CharacterListScreen(
                navHostController = navController,
                uiState = viewModel.uiState.collectAsState(),
            )
        }

        composable(route = Screen.DetailsScreen.route, arguments = listOf(
            navArgument(name = Screen.CHARACTER_ID) { type = NavType.IntType }
        )) {

            CountryDetailsScreen(navHostController = navController)
        }
    }
}
