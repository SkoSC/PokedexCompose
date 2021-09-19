package com.skosc.pokedex.domain.pokemon.entity.network

import com.skosc.pokedex.core.entity.ActiveRecord

internal data class PokeApiPokemonSpec(
    val pokemon: PokeApiPokemon,
    val species: PokeApiPokemonSpecies,
)