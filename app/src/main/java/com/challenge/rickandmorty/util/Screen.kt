package com.challenge.rickandmorty.util


sealed class Screen(val route: String) {

    companion object {
        const val CHARACTER_ID: String = "CHARACTER_ID"
    }

    data object RickMortyScreen : Screen("RickMortyScreen")

    data object DetailsScreen : Screen("DetailsScreen/{$CHARACTER_ID}") {
        fun passId(id: Int): String {
            return this.route.replace(oldValue = "{$CHARACTER_ID}", newValue = id.toString())
        }
    }
}