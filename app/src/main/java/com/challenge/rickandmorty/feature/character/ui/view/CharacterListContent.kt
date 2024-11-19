package com.challenge.rickandmorty.feature.character.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.challenge.rickandmorty.feature.character.domain.model.CharacterData

@Composable
fun CharacterListContent(
    characterPagingItems: LazyPagingItems<CharacterData>,
    modifier: Modifier = Modifier,
    onCharacterClick: (Int) -> Unit,
    listState: LazyListState = rememberLazyListState(),
) {

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
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
                        onClick = { onCharacterClick(data.id) }
                    )
                }
            }
        }
    }
}