package com.example.demoappcatalano.api

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

fun getPokemonNames(callback: (List<String>?, Throwable?) -> Unit) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://pokeapi.co/api/v2/pokemon?limit=20") // Puoi modificare il limite di quanti Pokémon ottenere
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            val gson = Gson()
            val json = response.body?.string()
            val pokemonList = gson.fromJson(json, PokemonListResponse::class.java)
            val pokemonNames = pokemonList?.results?.map { it.name }

            callback(pokemonNames, null)
        }

        override fun onFailure(call: Call, e: IOException) {
            callback(null, e)
        }
    })
}

data class PokemonListResponse(val results: List<Pokemon>)
data class Pokemon(val name: String)