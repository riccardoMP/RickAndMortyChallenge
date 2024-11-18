package com.challenge.rickandmorty.feature.character.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.challenge.rickandmorty.feature.character.domain.model.CharacterData
import com.country.styles.topbar.CustomTopAppBar

@Composable
@ExperimentalMaterial3Api
fun CharacterListScreen(
    navHostController: NavHostController,
    characterPagingItems: LazyPagingItems<CharacterData>,
    navigateToDetail: (Int) -> Unit,
) {

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navHostController,
                title = "Characters",
                actions = {
                    IconButton(onClick = { /* Handle search */ }) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                }
            )
        }
    ) { innerPadding ->

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
                    modifier = Modifier.padding(innerPadding)
                )
            }


        }
    }


}

@Composable
fun CharacterListContent(
    characterPagingItems: LazyPagingItems<CharacterData>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
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
