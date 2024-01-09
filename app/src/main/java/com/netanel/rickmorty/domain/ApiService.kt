package com.netanel.rickmorty.domain

import com.netanel.rickmorty.domain.model.character.Characters
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by netanelamar on 05/01/2024.
 * NetanelCA2@gmail.com
 */
/**
 * Retrofit API service interface for Rick and Morty characters.
 * Defines an endpoint for retrieving characters from the API.
 */
interface ApiService {

    // Retrofit GET annotation specifies the HTTP GET request to the "character" endpoint.
    @GET("character")
    fun getCharacters(): Call<Characters>

}