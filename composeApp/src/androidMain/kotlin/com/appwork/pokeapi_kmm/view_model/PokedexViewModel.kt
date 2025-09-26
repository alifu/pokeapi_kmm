package com.appwork.pokeapi_kmm.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appwork.pokeapi_kmm.PokemonSDK
import com.appwork.pokeapi_kmm.models.Pokedex
import com.appwork.pokeapi_kmm.models.PokedexResponse
import com.appwork.pokeapi_kmm.models.Pokemon
import com.appwork.pokeapi_kmm.models.PokemonSpecies
import kotlinx.coroutines.launch

class PokedexViewModel: ViewModel() {
    private val sdk = PokemonSDK()

    private val _list = MutableLiveData<List<Pokedex>>()  // <-- store a list
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
                Log.d("PokedexScreen", "Loaded Pokemon response: $res")
            } catch (e: Exception) {
                // handle error (post state)
                Log.e("PokedexScreen", "${e.message}")
            }
        }
    }

//    fun loadDetail(name: String, onResult: (Pokemon) -> Unit) {
//        viewModelScope.launch {
//            val p = sdk.fetchPokemon(name)
//            onResult(p)
//        }
//    }
//
//    fun loadSpecies(name: String, onResult: (PokemonSpecies) -> Unit) {
//        viewModelScope.launch {
//            val s = sdk.fetchSpecies(name)
//            onResult(s)
//        }
//    }
}
