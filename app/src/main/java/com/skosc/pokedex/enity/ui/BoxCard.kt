package com.skosc.pokedex.enity.ui

import androidx.compose.ui.graphics.Color
import com.skosc.pokedex.enity.domain.SearchResultIcon
import com.skosc.pokedex.navigation.Destination
import com.skosc.pokedex.navigation.ParcelableVoid
import com.skosc.pokedex.navigation.root.RootDestination
import com.skosc.pokedex.pokemonList
import com.skosc.pokedex.root
import com.skosc.pokedex.uikit.theme.PokeColor

sealed class BoxCard {

    data class Menu(
        val title: String,
        val color: Color,
        val destination: Destination<ParcelableVoid>
    ) : BoxCard() {

        companion object
    }

    data class SearchResult(
        val title: String,
        val icon: SearchResultIcon
    )
}

sealed class BoxCardList {

    data class Menu(private val wrapped: List<BoxCard.Menu> = emptyList()) : BoxCardList(), List<BoxCard.Menu> by wrapped

    data class SearchResult(private val wrapped: List<BoxCard.SearchResult> = emptyList()) : BoxCardList(), List<BoxCard.SearchResult> by wrapped
}

val BoxCard.Menu.Companion.sample get() = listOf(
    BoxCard.Menu("Pokemon", PokeColor.Teal, root.pokemonList),
    BoxCard.Menu("Items", PokeColor.Purple, root.pokemonList),
    BoxCard.Menu("Moves", PokeColor.Yellow, root.pokemonList),
    BoxCard.Menu("Abilities", PokeColor.Purple, root.pokemonList),
    BoxCard.Menu("Locations", PokeColor.Yellow, root.pokemonList)
)