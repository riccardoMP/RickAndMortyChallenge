package com.challenge.rickandmorty.feature.details.ui.view

import androidx.compose.foundation.background
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.rickandmorty.R
import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData.RowInformation
import com.country.styles.theme.CardBackgroundColor
import com.country.styles.theme.RickAndMortyTheme

@Composable
fun CharacterDetailsTextRow(information: RowInformation) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardBackgroundColor)
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = information.title),
            maxLines = 1,
            overflow = TextOverflow.Visible,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start
        )
        Text(
            text = information.value,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.End
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
fun DetailTextRowPreview() {
    RickAndMortyTheme {
        val data = RowInformation(title = R.string.details_gender, value = "Male")
        CharacterDetailsTextRow(information = data)
    }
}