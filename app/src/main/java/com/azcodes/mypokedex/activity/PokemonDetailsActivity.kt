package com.azcodes.mypokedex.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.azcodes.mypokedex.databinding.ActivityPokemonDetailsBinding
import com.azcodes.mypokedex.enum.LoadingState
import com.azcodes.mypokedex.model.PokemonDetailsVO
import com.azcodes.mypokedex.model.PokemonTypesVO
import com.azcodes.mypokedex.utils.Tools
import com.azcodes.mypokedex.viewmodel.PokemonDetailsViewModel

class PokemonDetailsActivity : AppCompatActivity() {

    private var pokemonId: Int = 0
    private lateinit var pokemonName: String

    private val viewModel: PokemonDetailsViewModel by lazy {
        ViewModelProvider(this)[PokemonDetailsViewModel::class.java]
    }

    private val binding: ActivityPokemonDetailsBinding by lazy {
        ActivityPokemonDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            pokemonId = bundle.getInt("id")
            pokemonName = bundle.getString("name").toString()
        }

        viewModel.fetchPokemonDetails(pokemonId)
        setupObservable()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    private fun setupObservable() {

        viewModel.loadingState.observe(this) {
            checkLoadingStatus(it)
        }

        viewModel.pokemonDetailsVOLiveData.observe(this) {
            if (it != null) {
                setupUI(it)
            }
        }
    }

    private fun setupUI(pokemonDetails: PokemonDetailsVO) {

        //Setup Pokemon Id
        pokemonDetails.id.let {
            binding.tvPokemonId.text = Tools.setupId(it)
        }

        //Setup Pokemon Name
        pokemonDetails.name.let {
            binding.tvPokemonName.text = Tools.toUppercaseText(it)
        }

        //Setup Pokemon Image
        pokemonDetails.sprites.frontDefault.let {
            binding.ivPokemon.load(it) {
                transformations(CircleCropTransformation())
            }
        }

        //Setup Pokemon Type
        displayPokemonType(pokemonDetails.type)

        //Setup Pokemon Stats
        pokemonDetails.stats.let {
            binding.tvPokemonStatHP.text = it[0].baseStat
            binding.tvPokemonStatAttack.text = it[1].baseStat
            binding.tvPokemonStatDefense.text = it[2].baseStat
            binding.tvPokemonStatSAttack.text = it[3].baseStat
            binding.tvPokemonStatSDefense.text = it[4].baseStat
            binding.tvPokemonStatSpeed.text = it[5].baseStat
        }
    }

    private fun displayPokemonType(list: List<PokemonTypesVO>) {

        if (list.size > 1) {
            val pokemonType1 = Tools.toUppercaseText(list[0].type.name)
            binding.typeLayout.tvPokemonType1.text = pokemonType1
            binding.typeLayout.cvPokemonTypes1.setCardBackgroundColor(
                resources.getColor(
                    Tools.displayTypes(
                        pokemonType1
                    )
                )
            )

            val pokemonType2 = Tools.toUppercaseText(list[1].type.name)
            binding.typeLayout.tvPokemonType2.text = pokemonType2
            binding.typeLayout.cvPokemonTypes2.setCardBackgroundColor(
                resources.getColor(
                    Tools.displayTypes(
                        pokemonType2
                    )
                )
            )

        } else {
            val pokemonType1 = Tools.toUppercaseText(list[0].type.name)
            binding.typeLayout.tvPokemonType1.text = pokemonType1
            binding.typeLayout.cvPokemonTypes1.setCardBackgroundColor(
                resources.getColor(
                    Tools.displayTypes(
                        pokemonType1
                    )
                )
            )

            binding.typeLayout.cvPokemonTypes2.visibility = View.GONE
        }

    }

    private fun checkLoadingStatus(loadingState: LoadingState) {
        when (loadingState) {
            LoadingState.Loading -> {
                binding.pbStatus.visibility = View.VISIBLE
            }

            LoadingState.Success -> {
                binding.pbStatus.visibility = View.GONE
            }

            LoadingState.Error -> {
                binding.pbStatus.visibility = View.GONE
            }
        }
    }
}