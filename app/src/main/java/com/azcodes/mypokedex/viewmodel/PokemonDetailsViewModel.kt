package com.azcodes.mypokedex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azcodes.mypokedex.enum.LoadingState
import com.azcodes.mypokedex.model.PokemonDetails
import com.azcodes.mypokedex.repository.ApiClient
import com.azcodes.mypokedex.repository.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonDetailsViewModel(
    private val apiRepository: ApiRepository
    = ApiRepository(ApiClient.apiInterface)
) : ViewModel() {

    private var _pokemonDetailLiveData = MutableLiveData<PokemonDetails?>()
    private var _loadingState = MutableLiveData<LoadingState>()

    val pokemonDetailsLiveData: LiveData<PokemonDetails?>
        get() = _pokemonDetailLiveData

    val loadingState: MutableLiveData<LoadingState>
        get() = _loadingState


    fun fetchPokemonDetails(pokemonId: Int) {
        _loadingState.postValue(LoadingState.Loading)

        val client = apiRepository.getGen1PokemonDetails("pokemon/$pokemonId")
        client.enqueue(object : Callback<PokemonDetails> {
            override fun onResponse(
                call: Call<PokemonDetails>,
                response: Response<PokemonDetails>
            ) {
                if (response.isSuccessful) {
                    _loadingState.postValue(LoadingState.Success)
                    _pokemonDetailLiveData.postValue(response.body())
                }
            }

            override fun onFailure(
                call: Call<PokemonDetails>,
                t: Throwable
            ) {
                _loadingState.postValue(LoadingState.Error)
                Log.i("Error", t.message.toString())
            }
        })
    }

}