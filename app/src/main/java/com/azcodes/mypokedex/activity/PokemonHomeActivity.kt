package com.azcodes.mypokedex.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.azcodes.mypokedex.adapter.IntroAdapter
import com.azcodes.mypokedex.databinding.ActivityPokemonHomeBinding
import com.azcodes.mypokedex.model.IntroObjects

class PokemonHomeActivity : AppCompatActivity() {

    private var viewPager2: ViewPager2? = null

    private val binding: ActivityPokemonHomeBinding by lazy {
        ActivityPokemonHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViewPager()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager2?.unregisterOnPageChangeCallback(pagerCallBack)
    }

    private val pagerCallBack = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            if (position == IntroObjects.introItems.size - 1) {
                binding.btnNext.text = "Finish"
                binding.btnNext.setOnClickListener {
                    startActivity(Intent(this@PokemonHomeActivity, PokemonMainActivity::class.java))
                }
            } else {
                binding.btnNext.text = "Next"
                binding.btnNext.setOnClickListener {
                    viewPager2?.currentItem = position + 1
                }
            }
        }
    }

    private fun setupViewPager() {

        val adapter = IntroAdapter(IntroObjects.introItems)
        binding.vpViewPager.adapter = adapter
        binding.vpViewPager.registerOnPageChangeCallback(pagerCallBack)
        binding.dotsIndicator.setViewPager2(binding.vpViewPager)
    }

}