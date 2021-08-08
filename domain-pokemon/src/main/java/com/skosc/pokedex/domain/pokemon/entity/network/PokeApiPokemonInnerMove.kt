package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeApiPokemonInnerMove(
    @SerialName("move") val move: PokeApiMoveShort
)