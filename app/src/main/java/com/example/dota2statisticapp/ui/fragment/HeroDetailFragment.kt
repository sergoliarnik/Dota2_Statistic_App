package com.example.dota2statisticapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dota2statisticapp.databinding.FragmentHeroDetailBinding

class HeroDetailFragment : Fragment() {

    companion object {
        const val heroNameArg = "heroName"
    }

    private lateinit var binding: FragmentHeroDetailBinding

    // Argument
    private lateinit var heroName: String

    // View
    private lateinit var heroNameTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            heroName = it.getString(heroNameArg).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        heroNameTextView = binding.heroNameTextView
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        heroNameTextView.text = heroName
    }
}