package com.azcodes.mypokedex.repository

class ApiRepository(private val apiInterface: ApiInterface) {

    fun getGen1Pokemon(limit: String, offset: String) = apiInterface.fetchGen1Pokemon(limit, offset)

    fun getGen1PokemonDetails(url: String) = apiInterface.fetchGen1PokemonDetails(url)
}