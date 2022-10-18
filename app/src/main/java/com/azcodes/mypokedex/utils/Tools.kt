package com.azcodes.mypokedex.utils

import com.azcodes.mypokedex.R
import com.azcodes.mypokedex.enum.PokemonTypes

object Tools {

    fun toUppercaseText(text: String): String {
        return text.replaceFirstChar { it.uppercase() }
    }

    fun setupId(id: Int): String {
        var formattedId = ""

        if (id <= 9) {
            formattedId = "#000$id"
        } else if (id <= 99) {
            formattedId = "#00$id"
        } else if (id <= 999) {
            formattedId = "#0$id"
        } else if (id <= 9999) {
            formattedId = "#$id"
        }

        return formattedId
    }

    fun displayTypes(pokemonType: String): Int {

        when (pokemonType) {
            PokemonTypes.Normal.name -> {
                return R.color.normal
            }

            PokemonTypes.Fire.name -> {
                return R.color.fire
            }

            PokemonTypes.Water.name -> {
                return R.color.water
            }

            PokemonTypes.Electric.name -> {
                return R.color.electric
            }

            PokemonTypes.Grass.name -> {
                return R.color.grass
            }

            PokemonTypes.Ice.name -> {
                return R.color.ice
            }

            PokemonTypes.Fighting.name -> {
                return R.color.fighting
            }

            PokemonTypes.Poison.name -> {
                return R.color.poison
            }

            PokemonTypes.Ground.name -> {
                return R.color.ground
            }

            PokemonTypes.Flying.name -> {
                return R.color.flying
            }

            PokemonTypes.Psychic.name -> {
                return R.color.psychic
            }

            PokemonTypes.Bug.name -> {
                return R.color.bug
            }

            PokemonTypes.Rock.name -> {
                return R.color.rock
            }

            PokemonTypes.Ghost.name -> {
                return R.color.ghost
            }

            PokemonTypes.Dragon.name -> {
                return R.color.dragon
            }

            PokemonTypes.Dark.name -> {
                return R.color.dark
            }

            PokemonTypes.Steel.name -> {
                return R.color.steel
            }

            PokemonTypes.Fairy.name -> {
                return R.color.fairy
            }

            else -> {
                return R.color.normal
            }
        }
    }

    fun setPokemonId(currentPosition: Int, region: String): Int {
        when (region) {
            "Kanto" -> {
                return currentPosition + 0
            }
            "Johto" -> {
                return currentPosition + 151
            }
            "Hoenn" -> {
                return currentPosition + 251
            }
            "Sinnoh" -> {
                return currentPosition + 386
            }
            "Unova" -> {
                return currentPosition + 494
            }
            "Kalos" -> {
                return currentPosition + 649
            }
            "Alola" -> {
                return currentPosition + 721
            }
            "Galar" -> {
                return currentPosition + 809
            }
            else -> {
                return currentPosition + 0
            }
        }
    }
}