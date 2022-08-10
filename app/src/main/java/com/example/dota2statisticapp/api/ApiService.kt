package com.example.dota2statisticapp.api

import com.example.dota2statisticapp.data.model.Heroes
import com.example.dota2statisticapp.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/heroes")
    suspend fun getHeroes(): Response<Heroes>

    @GET("api/players/{userId}")
    suspend fun getUser(@Path("userId") userId: String): Response<User>
}