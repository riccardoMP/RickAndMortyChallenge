package com.challenge.rickandmorty.feature.details.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.rickandmorty.feature.details.domain.model.CharacterDetailData
import com.challenge.rickandmorty.R
import com.country.styles.component.divider.RMDivider
import com.country.styles.theme.CardBackgroundColor
import com.country.styles.theme.RickAndMortyTheme

@Composable
fun CharacterDetailInfoView(data: CharacterDetailData) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.details_information),
            modifier = Modifier
                .padding(12.dp),
            style = MaterialTheme.typography.headlineMedium
        )
        Box(
            modifier = Modifier.fillMaxWidth().background(CardBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                with(data) {
                    TextRow(
                        key = stringResource(id = status.title),
                        value = status.value
                    )
                    RMDivider()

                    TextRow(
                        key = stringResource(id = name.title),
                        value = name.value
                    )

                    RMDivider()

                    TextRow(
                        key = stringResource(id = species.title),
                        value = species.value
                    )

                    RMDivider()

                    TextRow(
                        key = stringResource(id = gender.title),
                        value = gender.value
                    )
                }

            }
        }
    }
}

@Composable
private fun TextRow(key: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = key,
            maxLines = 1,
            overflow = TextOverflow.Visible,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start
        )
        Text(
            text = value,
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
fun CharacterDetailInfoViewPreview() {
    RickAndMortyTheme {
        Surface {
            CharacterDetailInfoView(
                CharacterDetailData.init()
            )
        }
    }
}