package com.azcodes.mypokedex.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// --------------------------------------------------------------------------------------------
// -------------------------------------- Pokemon List ----------------------------------------
// --------------------------------------------------------------------------------------------

data class PokemonListDAO(
    @Json(name = "name")
    var name: String,
    @Json(name = "url")
    val url: String
)

// --------------------------------------------------------------------------------------------
// ------------------------------------ Pokemon Details ---------------------------------------
// --------------------------------------------------------------------------------------------

@JsonClass(generateAdapter = true)
data class PokemonDetailsDAO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "sprites")
    val sprites: PokemonSpritesDAO,
    @Json(name = "types")
    val type: List<PokemonTypesDAO>,
    @Json(name = "stats")
    val stats: List<PokemonStatsDAO>
)

// --------------------------------------------------------------------------------------------
// ------------------------------------- Pokemon Types ----------------------------------------
// --------------------------------------------------------------------------------------------

data class PokemonTypesDAO(
    @Json(name = "type")
    val type: PokemonTypesCategoryDAO
)

data class PokemonTypesCategoryDAO(
    @Json(name = "name")
    val name: String,
)

// --------------------------------------------------------------------------------------------
// ------------------------------------- Pokemon Stats ----------------------------------------
// --------------------------------------------------------------------------------------------

data class PokemonStatsDAO(
    @Json(name = "base_stat")
    val baseStat: String
)

// --------------------------------------------------------------------------------------------
// ----------------------------------- Pokemon Sprites ----------------------------------------
// --------------------------------------------------------------------------------------------

data class PokemonSpritesDAO(
    @Json(name = "front_default")
    val frontDefault: String
)

// --------------------------------------------------------------------------------------------
// ----------------------------------- Pokemon Response ---------------------------------------
// --------------------------------------------------------------------------------------------

data class PokemonResponseDAO(
    @Json(name = "results")
    var result: List<PokemonListDAO>
)
