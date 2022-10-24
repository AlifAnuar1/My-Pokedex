package com.azcodes.mypokedex.repository

import com.azcodes.mypokedex.model.PokemonDetailsDAO
import com.azcodes.mypokedex.model.PokemonResponseDAO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiInterface {

    @GET("pokemon")
    fun fetchPokemonList(
        @Query("limit") limit: String,
        @Query("offset") offset: String
    ): Call<PokemonResponseDAO>

    @GET
    fun fetchPokemonDetails(@Url url: String): Call<PokemonDetailsDAO>
}