package com.azcodes.mypokedex.repository

class ApiRepository(private val apiInterface: ApiInterface) {

    fun getPokemonList(limit: String, offset: String) = apiInterface.fetchPokemonList(limit, offset)

    fun getPokemonDetails(url: String) = apiInterface.fetchPokemonDetails(url)
}