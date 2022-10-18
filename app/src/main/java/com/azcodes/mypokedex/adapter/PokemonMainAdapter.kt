package com.azcodes.mypokedex.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.azcodes.mypokedex.fragment.PokemonMainFragment

class PokemonMainAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 150

    override fun createFragment(position: Int) = PokemonMainFragment.newInstance(position)

}