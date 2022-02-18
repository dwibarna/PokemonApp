package com.sobarna.pokemonapp.data.remote;

import com.sobarna.pokemonapp.data.response.PokemonDetailResponse;
import com.sobarna.pokemonapp.data.response.PokemonListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
  //  https://pokeapi.co/api/v2/

  //  pokemon/2/
    @GET("pokemon?limit=4")
    Call<PokemonListResponse> getPokemonList(@Query("offset") int offset);

    @GET("pokemon/{name}")
    Call<PokemonDetailResponse> getDetailPokemon(@Path("name")String name);
}
