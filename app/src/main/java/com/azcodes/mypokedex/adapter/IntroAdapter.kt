package com.azcodes.mypokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azcodes.mypokedex.databinding.ItemIntroBinding
import com.azcodes.mypokedex.model.Intro

class IntroAdapter(private val introList: List<Intro>) :
    RecyclerView.Adapter<IntroAdapter.IntroViewHolder>() {

    inner class IntroViewHolder(private val binding: ItemIntroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(intro: Intro) {
            binding.tvTitle.text = intro.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        return IntroViewHolder(
            ItemIntroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.bindItem(introList[position])
    }

    override fun getItemCount(): Int {
        return introList.size
    }
}