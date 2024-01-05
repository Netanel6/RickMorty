package com.netanel.rickmorty.domain.model.character

import com.netanel.rickmorty.domain.DefaultRestError

/**
 * Created by netanelamar on 05/01/2024.
 * NetanelCA2@gmail.com
 */
data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
): DefaultRestError()