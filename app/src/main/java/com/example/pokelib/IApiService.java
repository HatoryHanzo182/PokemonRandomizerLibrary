package com.example.pokelib;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IApiService
{
    @GET("pokemon/{id}")
    Call<Pokemon> GetPokemonById(@Path("id") int pokemonId);
}
