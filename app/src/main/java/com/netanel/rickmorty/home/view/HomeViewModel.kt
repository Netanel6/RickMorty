package com.netanel.rickmorty.home.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netanel.rickmorty.domain.DefaultRestError
import com.netanel.rickmorty.domain.NetworkCallback
import com.netanel.rickmorty.domain.model.character.Character
import com.netanel.rickmorty.domain.model.character.Characters
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

    private val listOfCharacters: MutableLiveData<List<Character>> = MutableLiveData()
    val _listOfCharacters by lazy { listOfCharacters }

    private val state : MutableLiveData<HomeState> = MutableLiveData(HomeState.Loading)
    val _state by lazy { state }

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacters().enqueue(object : NetworkCallback<Characters>() {
                override fun onSuccess(result: Characters?) {
                    if (result?.results != null) {
                        listOfCharacters.value = result.results
                        state.postValue(HomeState.Loaded)
                    } else {
                        state.postValue(HomeState.Error)
                    }
                }

                override fun onFailure(error: DefaultRestError?) {
                    state.postValue(HomeState.Error)
                    Logger.info(TAG, error.toString())
                }
            })
        }
    }

    companion object {
         val TAG = HomeViewModel::class.java.simpleName.toString()
    }
}

enum class HomeState() {
    Loading,
    Loaded,
    Error
}