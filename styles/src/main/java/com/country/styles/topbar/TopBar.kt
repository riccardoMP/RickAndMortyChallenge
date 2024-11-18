package com.country.styles.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@ExperimentalMaterial3Api
@Composable
fun TopBar(navController: NavHostController, modifier: Modifier = Modifier) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val title: String = currentBackStackEntry?.destination?.label.toString()
    val showBackButton: Boolean = navController.previousBackStackEntry != null

    TopAppBar(
        modifier = modifier,
        title = { Text(title) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
    )
}


@Composable
@ExperimentalMaterial3Api
fun CustomTopAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    title: String,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    val showBackButton: Boolean = navController.previousBackStackEntry != null

    TopAppBar(
        modifier = modifier,
        title = { Text(title) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
        actions = { actions?.invoke(this) }
    )
}
