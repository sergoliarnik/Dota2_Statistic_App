package com.example.dota2statisticapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dota2statisticapp.data.model.Heroes
import com.example.dota2statisticapp.data.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class HeroesListViewModel : ViewModel() {
    var repository = Repository()
    val list: MutableLiveData<Response<Heroes>> = MutableLiveData()

    fun getHeroes(){
        viewModelScope.launch {
            list.value = repository.getHeroes()
        }
    }
}