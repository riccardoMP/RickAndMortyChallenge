package com.challenge.rickandmorty.feature.details.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData

@Composable
fun CharacterDetailContent(data: CharacterDetailData, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {

        item("header") {
            CharacterDetailHeaderView(imageUrl = data.imageUrl)
        }

        item("contentInfo") {
            CharacterDetailInfoView(data = data)
        }

        /*item("contentLocation") {
            CharacterDetailLocationView(character = character, navigator = navigator)
        }

        if (character.episodeDtoList.isNotEmpty()) {
            item("contentEpisode") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.text_episodes),
                        modifier = Modifier
                            .padding(12.dp),
                        style = JetRortyTypography.h6
                    )
                    character.episodeDtoList.forEach {
                        CharacterEpisodeView(dto = it, navigator = navigator)
                    }
                }
            }
        } */

    }
}