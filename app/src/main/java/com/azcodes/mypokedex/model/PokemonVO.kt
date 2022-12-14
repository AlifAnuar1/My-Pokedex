package com.azcodes.mypokedex.model

// --------------------------------------------------------------------------------------------
// -------------------------------------- Pokemon Main ----------------------------------------
// --------------------------------------------------------------------------------------------

data class PokemonVO(
    var name: String,
    val pokemonDetails: PokemonDetailsVO,
)

// --------------------------------------------------------------------------------------------
// -------------------------------------- Pokemon List ----------------------------------------
// --------------------------------------------------------------------------------------------

data class PokemonListVO(
    var name: String,
    val url: String
)

// --------------------------------------------------------------------------------------------
// ------------------------------------ Pokemon Details ---------------------------------------
// --------------------------------------------------------------------------------------------

data class PokemonDetailsVO(
    var id: Int,
    val name: String,
    val sprites: PokemonSpritesVO,
    val type: List<PokemonTypesVO>,
    val stats: List<PokemonStatsVO>
)

// --------------------------------------------------------------------------------------------
// ------------------------------------- Pokemon Types ----------------------------------------
// --------------------------------------------------------------------------------------------

data class PokemonTypesVO(
    val type: PokemonTypesCategoryVO
)

data class PokemonTypesCategoryVO(
    val name: String,
)

// --------------------------------------------------------------------------------------------
// ------------------------------------- Pokemon Stats ----------------------------------------
// --------------------------------------------------------------------------------------------

data class PokemonStatsVO(
    val baseStat: String
)

// --------------------------------------------------------------------------------------------
// ----------------------------------- Pokemon Sprites ----------------------------------------
// --------------------------------------------------------------------------------------------

data class PokemonSpritesVO(
    val frontDefault: String = ""
)

// --------------------------------------------------------------------------------------------
// ----------------------------------- Pokemon Response ---------------------------------------
// --------------------------------------------------------------------------------------------

data class PokemonResponseVO(
    var result: List<PokemonListDAO>
)

