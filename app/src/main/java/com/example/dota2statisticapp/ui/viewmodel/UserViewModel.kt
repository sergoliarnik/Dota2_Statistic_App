package com.example.dota2statisticapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dota2statisticapp.data.model.Heroes
import com.example.dota2statisticapp.data.model.User
import com.example.dota2statisticapp.data.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel: ViewModel() {
    var repository = Repository()
    val user: MutableLiveData<Response<User>> = MutableLiveData()

    fun getUser(userId: String){
        viewModelScope.launch {
            user.value = repository.getUser(userId)
        }
    }


}