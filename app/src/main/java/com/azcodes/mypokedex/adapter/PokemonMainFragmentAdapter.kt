package com.azcodes.mypokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azcodes.mypokedex.`interface`.OnItemClickedListener
import com.azcodes.mypokedex.databinding.ItemPokemonBinding
import com.azcodes.mypokedex.model.Pokemon
import com.azcodes.mypokedex.utils.Tools

class PokemonMainFragmentAdapter(
    private val pokemonList: List<Pokemon>
) :
    RecyclerView.Adapter<PokemonMainFragmentAdapter.PokemonMainFragmentViewHolder>() {

    private lateinit var mListener: OnItemClickedListener

    inner class PokemonMainFragmentViewHolder(
        private val binding: ItemPokemonBinding,
        listener: OnItemClickedListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(pokemon: Pokemon) {
            binding.tvPokemonName.text = Tools.toUppercaseText(pokemon.name)
            binding.tvPokemonUrl.text = pokemon.url
        }

        init {
            binding.root.setOnClickListener {
                listener.onItemClicked(adapterPosition)
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