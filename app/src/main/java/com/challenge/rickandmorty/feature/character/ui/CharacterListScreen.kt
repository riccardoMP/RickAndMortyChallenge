package com.challenge.rickandmorty.feature.character.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.challenge.rickandmorty.feature.character.domain.model.CharacterData

@Composable
fun CharacterListScreen(
    navHostController: NavHostController,
    characterPagingItems: LazyPagingItems<CharacterData>,
    navigateToDetail: (Int) -> Unit,
) {

    val onCountryClick: (Int) -> Unit = { id ->
        /*val route: String = Screen.DetailsScreen.passId(
            latitude = country.latitude,
            longitude = country.longitude
        )
        navHostController.navigate(route)*/
    }


    when {
        (characterPagingItems.loadState.refresh is LoadState.Error) -> {


        }

        characterPagingItems.loadState.refresh is LoadState.Loading -> {

        }

        else -> {
            CharacterListContent(
                characterPagingItems = characterPagingItems,
                navigateToDetail = navigateToDetail,
            )
        }


    }


}

@Composable
fun CharacterListContent(
    characterPagingItems: LazyPagingItems<CharacterData>,
    navigateToDetail: (Int) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (characterPagingItems.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(
                    count = characterPagingItems.itemCount,
                    key = characterPagingItems.itemKey { it.id },
                ) { index ->
                    characterPagingItems[index]?.let { data ->
                        CharacterItem(
                            character = data,
                            onClick = {
                                //navigateToDetail(pokemon.id)
                            },
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp),
                        )
                        /*PokemonItem(
                            data,
                            onClick = {
                                //navigateToDetail(pokemon.id)
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )*/
                        Divider(
                            color = MaterialTheme.colorScheme.secondary,
                            thickness = 0.2.dp,
                            modifier = Modifier.padding(horizontal = 20.dp),
                        )
                    }
                }
                item {
                    if (characterPagingItems.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}
