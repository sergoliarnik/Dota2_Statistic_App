package com.example.dota2statisticapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dota2statisticapp.R
import com.example.dota2statisticapp.data.model.HeroesItem
import com.example.dota2statisticapp.util.Util
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class HeroesAdapter(val clickListener: (HeroesItem) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var filteredListOfHeroes = emptyList<HeroesItem>()

    // TODO("Think about better way of filtering )
    var filter: String = ""
        set(item) {
            field = item
            filterList()
        }

    var listOfHeroes = ArrayList<HeroesItem>()
        set(list) {
            field = list
            filterList()
        }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterList() {
        filteredListOfHeroes =
            listOfHeroes.filter { it.localized_name.lowercase().contains(filter.lowercase()) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HeroesViewHolder(parent) { clickListener(filteredListOfHeroes[it]) }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HeroesViewHolder).onBind(filteredListOfHeroes[position])
    }

    override fun getItemCount() = filteredListOfHeroes.size

    class HeroesViewHolder(parent: ViewGroup, onClickAtPosition: (Int) -> Unit) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_hero, parent, false)
        ) {
        init {
            itemView.setOnClickListener { onClickAtPosition(adapterPosition) }
        }

        private val heroImageImageView: ImageView = itemView.findViewById(R.id.heroImageImageView)
        private val heroNameTextView: TextView = itemView.findViewById(R.id.heroNameTextView)

        fun onBind(hero: HeroesItem) {
            heroNameTextView.text = hero.localized_name
            Picasso.with(itemView.context)
                .load(Util.getHeroImageUrlByHeroName(hero.localized_name))
                .into(heroImageImageView)
        }
    }

}