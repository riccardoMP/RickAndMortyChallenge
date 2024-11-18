package com.challenge.rickandmorty.feature.details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.challenge.rickandmorty.R
import com.country.styles.topbar.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetailsScreen(navHostController: NavHostController) {

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navHostController,
                title = stringResource(R.string.details_title),
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text("Details")
        }

    }
}
