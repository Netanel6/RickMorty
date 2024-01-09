package com.netanel.rickmorty.domain.model.character

import com.netanel.rickmorty.domain.DefaultRestError

data class Characters(
    val info: Info,
    val results: List<Character>
): DefaultRestError()