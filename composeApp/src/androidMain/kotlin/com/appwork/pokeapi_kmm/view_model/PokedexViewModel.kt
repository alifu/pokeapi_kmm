package com.appwork.pokeapi_kmm.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appwork.pokeapi_kmm.PokemonSDK
import com.appwork.pokeapi_kmm.models.Pokedex
import kotlinx.coroutines.launch

class PokedexViewModel: ViewModel() {
    private val sdk = PokemonSDK()

    private val _list = MutableLiveData<List<Pokedex>>()
    val list: LiveData<List<Pokedex>> = _list
    var searchText by mutableStateOf("")
        private set

    var showSortMenu by mutableStateOf(false)
        private set

    // Update functions
    fun updateSearchText(newText: String) {
        searchText = newText
    }

    fun toggleSortMenu() {
        showSortMenu = !showSortMenu
    }

    fun loadPage(limit: Int = 50, offset: Int = 0) {
        viewModelScope.launch {
            try {
                val res = sdk.fetchPokedex(limit, offset)
                val currentList = _list.value.orEmpty()
                val updatedList = currentList + res.results
                _list.postValue(updatedList)
            } catch (e: Exception) {
                // handle error (post state)
            }
        }
    }
}
