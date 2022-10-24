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

class PokemonDetailsViewModel(
    private val apiRepository: ApiRepository
    = ApiRepository(ApiClient.apiInterface)
) : ViewModel() {

    private var _pokemonDetailVOLiveData = MutableLiveData<PokemonDetailsVO>()
    private var _loadingState = MutableLiveData<LoadingState>()

    val pokemonDetailsVOLiveData: LiveData<PokemonDetailsVO>
        get() = _pokemonDetailVOLiveData

    val loadingState: MutableLiveData<LoadingState>
        get() = _loadingState

    fun fetchPokemonDetails(pokemonId: Int) {

        val client = apiRepository.getPokemonDetails("pokemon/$pokemonId")
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

                            _pokemonDetailVOLiveData.postValue(pokemonDetails)
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