package com.challenge.rickandmorty.feature.character.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.challenge.rickandmorty.feature.character.domain.model.CharacterData

@Composable
fun CharacterItem(
    character: CharacterData,
    onClick: (Int) -> Unit,
) {

    AnimatedVisibility(
        visible = true,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Card(
            modifier = Modifier
                .animateContentSize()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .clickable {
                    onClick(character.id)
                },
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = character.imageUrl,
                    modifier = Modifier.fillMaxWidth(0.25f),
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                )
                CharacterInfo(data = character, modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp))
            }
        }
    }
}