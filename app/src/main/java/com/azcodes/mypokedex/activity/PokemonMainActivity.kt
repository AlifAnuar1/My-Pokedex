package com.azcodes.mypokedex.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.azcodes.mypokedex.adapter.PokemonMainAdapter
import com.azcodes.mypokedex.databinding.ActivityPokemonMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class PokemonMainActivity : AppCompatActivity() {

    private val binding: ActivityPokemonMainBinding by lazy {
        ActivityPokemonMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupUI()

    }

    private fun setupUI() {

        val adapter = PokemonMainAdapter(this)
        binding.vpViewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.vpViewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(position: Int): String {
        return when (position) {
            0 -> "Kanto"
            1 -> "Johto"
            2 -> "Hoenn"
            3 -> "Sinnoh"
            4 -> "Unova"
            5 -> "Kalos"
            6 -> "Alola"
            7 -> "Galar"
            else -> ""
        }
    }
}
