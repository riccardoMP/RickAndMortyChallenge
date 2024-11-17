package com.challenge.rickandmorty.feature.main.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.challenge.rickandmorty.feature.character.ui.CharacterListScreen
import com.challenge.rickandmorty.feature.character.viewmodel.CharacterViewModel
import com.challenge.rickandmorty.util.Screen

@Composable
fun RickMortyNavHost(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.RickMortyScreen.route
    ) {
        composable(route = Screen.RickMortyScreen.route) {
            val viewModel = hiltViewModel<CharacterViewModel>()

            CharacterListScreen (
                navHostController = navController,
                characterPagingItems = viewModel.characterPagingDataFlow.collectAsLazyPagingItems(),
                navigateToDetail = {}
            )
        }

        composable(Screen.DetailsScreen.route) { backStackEntry ->
            /*val latitude = backStackEntry.arguments?.getString(LATITUDE)?.toDoubleOrNull() ?: 0.0
            val longitude = backStackEntry.arguments?.getString(LONGITUDE)?.toDoubleOrNull() ?: 0.0

            CountryDetailsScreen(
                navHostController = navigation,
                latitude = latitude,
                longitude = longitude
            )*/
        }
    }
}
