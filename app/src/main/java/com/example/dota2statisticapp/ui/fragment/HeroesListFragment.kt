package com.example.dota2statisticapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dota2statisticapp.adapters.HeroesAdapter
import com.example.dota2statisticapp.databinding.FragmentHeroesListBinding
import com.example.dota2statisticapp.ui.viewmodel.HeroesListViewModel

class HeroesListFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModel: HeroesListViewModel
    private lateinit var binding: FragmentHeroesListBinding
    private lateinit var adapter: HeroesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroesListBinding.inflate(inflater, container, false)
        val view = binding.root
        recyclerView = binding.heroesRv
        searchView = binding.heroesSearchSearchView
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HeroesListViewModel::class.java)
        setRecyclerView()
    }


    private fun setRecyclerView() {
        setAdapter()
        setDataToAdapter()
        setSearcher()
    }

    private fun setSearcher() {
        binding.heroesSearchSearchView.setOnQueryTextListener(object :
            OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter = newText!!
                return true
            }
        })
    }

    private fun setAdapter() {
        adapter = HeroesAdapter {
            val action = HeroesListFragmentDirections
                .actionHeroesListFragmentToHeroDetailFragment(it.localized_name)
            requireView().findNavController().navigate(action)
        }
        recyclerView.adapter = adapter
    }

    private fun setDataToAdapter() {
        viewModel.getHeroes()
        viewModel.list.observe(viewLifecycleOwner) {
            adapter.listOfHeroes = it.body()!!
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.list.removeObservers(viewLifecycleOwner)
    }
}