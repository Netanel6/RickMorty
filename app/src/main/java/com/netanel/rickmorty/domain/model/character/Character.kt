package com.netanel.rickmorty.domain.model.character

/**
 * Created by netanelamar on 05/01/2024.
 * NetanelCA2@gmail.com
 */
data class Character(
    val created: String,
    val episode: List<String>,
    val gender: Gender,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: Species,
    val status: Status,
    val type: String,
    val url: String
) {

    enum class Status {
        Dead,
        Alive,
        unknown
    }

    val isAlive: Boolean
        get() = status == Status.Alive

    val isDead: Boolean
        get() = status == Status.Dead


    enum class Species {
        Human,
        Alien,
        unknown
    }

    val isHuman: Boolean
        get() = species == Species.Human

    val isAlien: Boolean
        get() = species == Species.Alien

    enum class Gender {
        Male,
        Female,
        unknown
    }

    val isMale: Boolean
        get() = gender == Gender.Male

    val isFemale: Boolean
        get() = gender == Gender.Female
}