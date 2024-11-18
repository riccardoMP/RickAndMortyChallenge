package com.challenge.rickandmorty.feature.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.challenge.rickandmorty.feature.main.ui.screen.RickMortyNavHost
import com.challenge.rickandmorty.feature.main.ui.theme.RickAndMortyTheme
import com.country.styles.topbar.TopBar
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                RickMortyNavHost()

                /*Scaffold(topBar = { TopBar(navController = navController) }) { innerPadding ->
                    RickMortyNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }*/
            }
        }
    }
}
