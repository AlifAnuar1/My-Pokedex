package com.azcodes.mypokedex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azcodes.mypokedex.enum.LoadingState
import com.azcodes.mypokedex.model.Pokemon
import com.azcodes.mypokedex.model.PokemonResponse
import com.azcodes.mypokedex.repository.ApiClient
import com.azcodes.mypokedex.repository.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonMainViewModel(
    private val apiRepository: ApiRepository
    = ApiRepository(ApiClient.apiInterface)
) : ViewModel() {

    private var _pokemonLiveData = MutableLiveData<List<Pokemon>?>()
    private var _loadingState = MutableLiveData<LoadingState>()

    val pokemonLiveData: LiveData<List<Pokemon>?>
        get() = _pokemonLiveData

    val loadingState: MutableLiveData<LoadingState>
        get() = _loadingState

    fun fetchPokemon(region: String) {

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

        val client = apiRepository.getGen1Pokemon(limit.toString(), offset.toString())
        client.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if (response.isSuccessful) {
                    _loadingState.postValue(LoadingState.Success)

                    _pokemonLiveData.postValue(response.body()?.result)
                }
            }

            override fun onFailure(
                call: Call<PokemonResponse>,
                t: Throwable
            ) {
                _loadingState.postValue(LoadingState.Error)
                Log.i("Error", t.message.toString())
            }
        })
    }

}