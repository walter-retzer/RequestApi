package com.example.exercicio03.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercicio03.data.Cities
import com.example.exercicio03.databinding.CardRecyclerviewBinding

class RecyclerViewAdapter(
    private val cities: ArrayList<Cities>,
    private val action: (Cities) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding =
            CardRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding, action)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CityViewHolder -> holder.bind(cities[position])
        }
    }

    override fun getItemCount(): Int = cities.size

    // Class que irá atualizar as váriáveis com os dados recebidos pela class RecyclerViewAdapter:
    inner class CityViewHolder(
        private val binding: CardRecyclerviewBinding,
        private val action: (Cities) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Cities) {
            binding.cityName.text = item.city
            binding.card.setOnClickListener {
                action.invoke(item)
            }
        }
    }
}
