package com.example.dota2statisticapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dota2statisticapp.adapters.HeroesAdapter
import com.example.dota2statisticapp.databinding.ActivityMainBinding
import com.example.dota2statisticapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    val adapter = HeroesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setContentView(viewBinding.root)
        setRecyclerView()
        viewBinding.heroesSearchSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter = newText!!
                return true
            }

        })
    }

    private fun setRecyclerView() {
        viewBinding.heroesRv.adapter = adapter
        viewModel.getHeroes()
        viewModel.list.observe(this, {
            adapter.listOfHeroes = it.body()!!
        })
    }
}