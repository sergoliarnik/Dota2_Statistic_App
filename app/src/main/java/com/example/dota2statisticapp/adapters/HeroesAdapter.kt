package com.example.dota2statisticapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dota2statisticapp.R
import com.example.dota2statisticapp.data.model.Heroes
import com.example.dota2statisticapp.data.model.HeroesItem
import com.example.dota2statisticapp.util.Util
import com.example.dota2statisticapp.util.heroesImageUrl
import com.squareup.picasso.Picasso
import kotlinx.coroutines.NonDisposableHandle.parent

class HeroesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var filteredListOfHeroes = emptyList<HeroesItem>()

    // TODO("Think about better way of filtering)
    var filter: String = ""
        set(item){
            field = item
            filteredListOfHeroes = listOfHeroes.filter { it.localized_name.contains(item) }
            notifyDataSetChanged()
        }

    var listOfHeroes = ArrayList<HeroesItem>()
        set(list) {
            field = list
            filteredListOfHeroes = list
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HeroesViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HeroesViewHolder).onBind(filteredListOfHeroes[position].localized_name)
    }

    override fun getItemCount() = filteredListOfHeroes.size

    inner class HeroesViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_hero, parent, false)
    ) {
        private val heroImageImageView: ImageView = itemView.findViewById(R.id.heroImageImageView)
        private val heroNameTextView: TextView = itemView.findViewById(R.id.heroNameTextView)

        fun onBind(heroName: String) {
            heroNameTextView.text = heroName
            Picasso.with(itemView.context)
                .load(Util.getHeroImageUrlByHeroName(heroName))
                .into(heroImageImageView)
        }
    }

}