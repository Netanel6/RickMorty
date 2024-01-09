package com.netanel.rickmorty.home.repository

import com.netanel.rickmorty.domain.ApiService
import com.netanel.rickmorty.domain.model.character.Characters
import retrofit2.Call


/**
 * Created by netanelamar on 05/01/2024.
 * NetanelCA2@gmail.com
 */
class HomeRepositoryImpl(private val networkManager: ApiService): HomeRepository {

    override suspend fun getCharacters(): Call<Characters> {
        return networkManager.getCharacters()
    }
}