package com.netanel.rickmorty.domain

import com.netanel.rickmorty.domain.model.character.Character
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by netanelamar on 05/01/2024.
 * NetanelCA2@gmail.com
 */
interface ApiService {

    @GET("character")
    fun getCharacters(): Call<Character>

}