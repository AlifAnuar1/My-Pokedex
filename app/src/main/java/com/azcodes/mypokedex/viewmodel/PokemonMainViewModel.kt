package com.azcodes.mypokedex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azcodes.mypokedex.enum.LoadingState
import com.azcodes.mypokedex.model.*
import com.azcodes.mypokedex.repository.ApiClient
import com.azcodes.mypokedex.repository.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonMainViewModel(
    private val apiRepository: ApiRepository
    = ApiRepository(ApiClient.apiInterface)
) : ViewModel() {

    private var _pokemonDetailsListLiveData = MutableLiveData<ArrayList<PokemonDetailsVO>?>()
    private var _loadingState = MutableLiveData<LoadingState>()

    val pokemonDetailsListLiveData: LiveData<ArrayList<PokemonDetailsVO>?>
        get() = _pokemonDetailsListLiveData

    val loadingState: MutableLiveData<LoadingState>
        get() = _loadingState

    val pokemonList: ArrayList<PokemonDetailsVO> = ArrayList()

    fun fetchPokemonDetailsList(region: String) {

        _loadingState.postValue(LoadingState.Loading)

        var limit = 0
        var offset = 0

        when (region) {
            "Kanto" -> {
                limit = 151
                offset = 0
            }
            "Johto" -> {
                limit = 100
                offset = 151
            }
            "Hoenn" -> {
                limit = 135
                offset = 251
            }
            "Sinnoh" -> {
                limit = 108
                offset = 386
            }
            "Unova" -> {
                limit = 155
                offset = 494
            }
            "Kalos" -> {
                limit = 72
                offset = 649
            }
            "Alola" -> {
                limit = 88
                offset = 721
            }
            "Galar" -> {
                limit = 95
                offset = 809
            }
        }

        for (i in (offset + 1) until (offset + limit)) {
            val client = apiRepository.getPokemonDetails("pokemon/$i")
            client.enqueue(object : Callback<PokemonDetailsDAO> {
                override fun onResponse(
                    call: Call<PokemonDetailsDAO>,
                    response: Response<PokemonDetailsDAO>
                ) {
                    if (response.isSuccessful) {

                        val pokemonDetailsDAO: PokemonDetailsDAO

                        if (response.body() != null) {

                            pokemonDetailsDAO = response.body()!!
                            pokemonDetailsDAO.let {

                                val pokemonId = it.id
                                val pokemonName = it.name
                                val pokemonSprites = PokemonSpritesVO(
                                    it.sprites.frontDefault
                                )

                                val pokemonType: ArrayList<PokemonTypesVO> = ArrayList()
                                for (i in it.type.indices) {

                                    val type = it.type[i].type.name
                                    val typesCategory = PokemonTypesCategoryVO(type)
                                    pokemonType.add(PokemonTypesVO(typesCategory))
                                }

                                val pokemonStats: ArrayList<PokemonStatsVO> = ArrayList()
                                for (i in it.stats.indices) {

                                    val stats = it.stats[i].baseStat
                                    pokemonStats.add(PokemonStatsVO(stats))
                                }

                                val pokemonDetails = PokemonDetailsVO(
                                    pokemonId,
                                    pokemonName,
                                    pokemonSprites,
                                    pokemonType,
                                    pokemonStats
                                )

                                pokemonList.add(pokemonDetails)
                                _pokemonDetailsListLiveData.postValue(pokemonList)

                            }
                        }
                    }
                }

                override fun onFailure(
                    call: Call<PokemonDetailsDAO>,
                    t: Throwable
                ) {
                    Log.i("Error", t.message.toString())
                }
            })
        }
    }

}