package com.example.dota2statisticapp.data.repository

import com.example.dota2statisticapp.api.RetrofitInstance
import com.example.dota2statisticapp.data.model.Heroes
import retrofit2.Response

class Repository {
    suspend fun getHeroes(): Response<Heroes> {
        return RetrofitInstance.api.getHeroes()
    }
}