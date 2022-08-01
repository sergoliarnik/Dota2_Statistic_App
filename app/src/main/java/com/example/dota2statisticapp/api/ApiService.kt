package com.example.dota2statisticapp.api

import com.example.dota2statisticapp.data.model.Heroes
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/heroes")
    suspend fun getHeroes(): Response<Heroes>
}