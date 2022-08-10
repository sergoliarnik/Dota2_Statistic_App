package com.example.dota2statisticapp.data.model

data class User(
    val mmr_estimate: MmrEstimate,
    val profile: Profile,
    val rank_tier: Int
)