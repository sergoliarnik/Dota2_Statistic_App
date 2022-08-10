package com.example.dota2statisticapp.data.model

data class Profile(
    val account_id: Int,
    val avatarfull: String,
    val loccountrycode: String,
    val personaname: String,
    val plus: Boolean,
    val profileurl: String,
    val steamid: String
)