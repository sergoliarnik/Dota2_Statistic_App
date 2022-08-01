package com.example.dota2statisticapp.data.model

data class HeroesItem(
    val attack_type: String,
    val id: Int,
    val localized_name: String,
    val name: String,
    val primary_attr: String,
    val roles: List<String>
)