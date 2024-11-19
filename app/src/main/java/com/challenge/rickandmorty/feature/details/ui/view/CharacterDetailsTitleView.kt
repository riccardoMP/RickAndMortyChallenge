package com.challenge.rickandmorty.feature.details.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.rickandmorty.R
import com.country.styles.theme.RickAndMortyTheme

@Composable
fun CharacterDetailsTitle(title: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = title),
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun DetailsTitlePreview() {
    RickAndMortyTheme {
        CharacterDetailsTitle(title = R.string.details_information)
    }
}