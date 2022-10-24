package com.azcodes.mypokedex.fragment

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.azcodes.mypokedex.`interface`.OnItemClickedListener
import com.azcodes.mypokedex.activity.PokemonDetailsActivity
import com.azcodes.mypokedex.adapter.PokemonMainFragmentAdapter
import com.azcodes.mypokedex.databinding.FragmentPokemonMainBinding
import com.azcodes.mypokedex.model.PokemonMainDetails
import com.azcodes.mypokedex.viewmodel.PokemonMainViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class PokemonMainFragment : Fragment() {

    private lateinit var pokemonList: ArrayList<PokemonMainDetails>

    private val binding: FragmentPokemonMainBinding by lazy {
        FragmentPokemonMainBinding.inflate(layoutInflater)
    }

    private val viewModel: PokemonMainViewModel by lazy {
        ViewModelProvider(this)[PokemonMainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
    }

    private fun setupAdapter() {
        val pos = arguments?.getInt(POSITION_ARG)

        pos?.let {
            when (pos) {
                0 -> setupViewModel("Kanto")
                1 -> setupViewModel("Johto")
                2 -> setupViewModel("Hoenn")
                3 -> setupViewModel("Sinnoh")
                4 -> setupViewModel("Unova")
                5 -> setupViewModel("Kalos")
                6 -> setupViewModel("Alola")
                7 -> setupViewModel("Galar")
            }
        }
    }

    private fun setupViewModel(region: String) {

        //checkSharedPreference(region)

        viewModel.fetchPokemonDetailsList(region)
        viewModel.pokemonDetailsListLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                val adapter = PokemonMainFragmentAdapter(it)
                binding.vpPokemonMainFragment.adapter = adapter

                //saveIntoSharedPreference(region, it)

                adapter.setupOnItemClickListener(object : OnItemClickedListener {
                    override fun onItemClicked(position: Int) {

                        val currentPokemonId = it[position].id

                        val intent = Intent(context, PokemonDetailsActivity::class.java)
                        intent.putExtra("id", currentPokemonId)
                        intent.putExtra("name", it[position].name)
                        startActivity(intent)
                    }
                })
            }
        }
    }

    private fun saveIntoSharedPreference(region: String, list: ArrayList<PokemonMainDetails>?) {

        pokemonList.clear()

        if (list != null) {
            for (i in list.indices) {
                val pokemonMainDetails = PokemonMainDetails(i, list[i].pokemonName, region)

                pokemonList.add(pokemonMainDetails)
            }
        }

        val sharedPreferences = context?.getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

        val gson = Gson()
        val json: String = gson.toJson(pokemonList)
        editor?.putString(region, json)
        editor?.apply()
    }

    private fun checkSharedPreference(region: String) {

        val sharedPreferences = context?.getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()

        val json = sharedPreferences?.getString(region, null)
        val type: Type = object : TypeToken<ArrayList<PokemonMainDetails?>?>() {}.type

        pokemonList = gson.fromJson<Any>(json, type) as ArrayList<PokemonMainDetails>
    }

    companion object {
        var POSITION_ARG = "position_arg"

        @JvmStatic
        fun newInstance(position: Int) = PokemonMainFragment().apply {
            arguments = Bundle().apply {
                putInt(POSITION_ARG, position)
            }
        }
    }
}