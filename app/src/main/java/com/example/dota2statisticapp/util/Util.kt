package com.example.dota2statisticapp.util

import com.example.dota2statisticapp.data.model.User
import java.util.*

object Util {


    fun getHeroImageUrlByHeroName(heroName: String): String {
        return "$heroesImageUrl${getConvertedHeroName(heroName)}.png"
    }

    fun getConvertedHeroName(heroName: String): String {
        val heroNameWithoutSpaces = heroName
            .toLowerCase()
            .replace("\\s".toRegex(), "_")
        return heroNameConverter.getOrDefault(heroNameWithoutSpaces, heroNameWithoutSpaces)
    }

    private val heroNameConverter: Map<String, String> = mapOf(
        "anti-mage" to "antimage",
        "crystalmaiden" to "crystal_maiden",
        "drowranger" to "drow_ranger",
        "shadow_fiend" to "nevermore",
        "windranger" to "windrunner",
        "zeus" to "zuus",
        "necrophos" to "necrolyte",
        "queen_of_pain" to "queenofpain",
        "wraith_king" to "skeleton_king",
        "clockwerk" to "rattletrap",
        "nature's_prophet" to "furion",
        "lifestealer" to "life_stealer",
        "doom" to "doom_bringer",
        "outworld_destroyer" to "obsidian_destroyer",
        "treant_protector" to "treant",
        "io" to "wisp",
        "centaur_warrunner" to "centaur",
        "magnus" to "magnataur",
        "timbersaw" to "shredder",
        "vengeful_spirit" to "vengefulspirit",
        "underlord" to "abyssal_underlord",
    )

    public fun getCountry(user: User): String {
        return getFlag(user) + getCountryAbbreviatura(user) + ", " + getCountryName(user)
    }

    private fun getFlag(user: User): String {
        val countryCode: String = user.profile.loccountrycode
        val firstLetter = Character.codePointAt(countryCode, 0) - 0x41 + 0x1F1E6
        val secondLetter = Character.codePointAt(countryCode, 1) - 0x41 + 0x1F1E6
        return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
    }

    private fun getCountryName(user: User): String {
        val loc = Locale("", user.profile.loccountrycode)
        return loc.displayCountry
    }

    private fun getCountryAbbreviatura(user: User): String {
        return user.profile.loccountrycode
    }
}