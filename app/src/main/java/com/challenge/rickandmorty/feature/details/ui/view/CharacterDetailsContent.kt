package com.challenge.rickandmorty.feature.details.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.challenge.rickandmorty.R
import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData
import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData.Divider
import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData.ImageUrl
import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData.RowInformation
import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData.Title
import com.country.styles.component.divider.RMDivider
import com.country.styles.theme.RickAndMortyTheme

@Composable
fun CharacterDetailContent(list: List<CharacterDetailData>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = list) { data ->
            CharacterDetailItem(data)
        }
    }
}

@Composable
fun CharacterDetailItem(data: CharacterDetailData) {
    when (data) {
        is ImageUrl -> CharacterDetailHeaderView(imageUrl = data.imageUrl)
        is Title -> CharacterDetailsTitle(title = data.title)
        is RowInformation -> CharacterDetailsTextRow(information = data)
        is Divider -> RMDivider()
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    RickAndMortyTheme {
        val list = listOf(
            Title(R.string.details_information),
            RowInformation(R.string.details_status, "status"),
            Divider,
            RowInformation(R.string.details_name, "name"),
            Divider,
            RowInformation(R.string.details_species, "species"),
            Divider,
            RowInformation(R.string.details_gender, "gender"),
            Title(R.string.details_location),
            RowInformation(R.string.details_location, "location"),
            Title(R.string.details_origin),
            RowInformation(R.string.details_origin, "origin")
        )
        CharacterDetailContent(list = list)
    }
}