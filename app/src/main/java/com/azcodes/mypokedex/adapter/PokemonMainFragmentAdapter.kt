package com.azcodes.mypokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.azcodes.mypokedex.`interface`.OnItemClickedListener
import com.azcodes.mypokedex.databinding.ItemPokemonBinding
import com.azcodes.mypokedex.model.PokemonDetailsVO
import com.azcodes.mypokedex.model.PokemonTypesVO
import com.azcodes.mypokedex.utils.Tools


class PokemonMainFragmentAdapter(
    private val pokemonList: ArrayList<PokemonDetailsVO>
) :
    RecyclerView.Adapter<PokemonMainFragmentAdapter.PokemonMainFragmentViewHolder>() {

    private lateinit var mListener: OnItemClickedListener

    inner class PokemonMainFragmentViewHolder(
        private val binding: ItemPokemonBinding,
        listener: OnItemClickedListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(pokemon: PokemonDetailsVO) {

            //Setup Pokemon Name
            binding.tvPokemonName.text = Tools.toUppercaseText(pokemon.name)

            //Setup Pokemon Image
            binding.ivPokemon.load(pokemon.sprites.frontDefault) {
                transformations(CircleCropTransformation())
            }

            //Setup Pokemon Type
            displayPokemonType(pokemon.type)
        }

        init {
            binding.root.setOnClickListener {
                listener.onItemClicked(adapterPosition)
            }
        }

        fun displayPokemonType(list: List<PokemonTypesVO>) {

            if (list.size > 1) {
                val pokemonType1 = Tools.toUppercaseText(list[0].type.name)
                binding.typeLayout.tvPokemonType1.text = pokemonType1
                binding.typeLayout.cvPokemonTypes1.setCardBackgroundColor(
                    binding.root.context.resources.getColor(
                        Tools.displayTypes(
                            pokemonType1
                        )
                    )
                )

                val pokemonType2 = Tools.toUppercaseText(list[1].type.name)
                binding.typeLayout.tvPokemonType2.text = pokemonType2
                binding.typeLayout.cvPokemonTypes2.setCardBackgroundColor(
                    binding.root.context.resources.getColor(
                        Tools.displayTypes(
                            pokemonType2
                        )
                    )
                )

            } else {
                val pokemonType1 = Tools.toUppercaseText(list[0].type.name)
                binding.typeLayout.tvPokemonType1.text = pokemonType1
                binding.typeLayout.cvPokemonTypes1.setCardBackgroundColor(
                    binding.root.context.resources.getColor(
                        Tools.displayTypes(
                            pokemonType1
                        )
                    )
                )

                binding.typeLayout.cvPokemonTypes2.visibility = View.GONE
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonMainFragmentViewHolder {
        return PokemonMainFragmentViewHolder(
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            mListener
        )
    }

    override fun onBindViewHolder(holder: PokemonMainFragmentViewHolder, position: Int) {
        holder.bindItem(pokemonList[position])
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun setupOnItemClickListener(listener: OnItemClickedListener) {
        mListener = listener
    }

}