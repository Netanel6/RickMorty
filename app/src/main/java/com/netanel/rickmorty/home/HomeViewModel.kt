package com.netanel.rickmorty.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netanel.rickmorty.domain.DefaultRestError
import com.netanel.rickmorty.domain.NetworkCallback
import com.netanel.rickmorty.domain.model.character.Character
import com.netanel.rickmorty.home.repository.HomeRepository
import com.netanel.rickmorty.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by netanelamar on 05/01/2024.
 * NetanelCA2@gmail.com
 */

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository): ViewModel() {

    fun getCharacters(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacters().enqueue(object: NetworkCallback<Character>(){
                override fun onSuccess(result: Character?) {
                    if (result != null) {
                        Logger.info("Netanel", result.toString())
                    }
                }
                override fun onFailure(error: DefaultRestError?) {
                    Log.i(TAG, "onFailure: $error")
                }
            })
        }
    }
}