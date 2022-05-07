package com.example.testtransbank.Interface;

import com.example.testtransbank.model.Characters;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RickAndMortyApiServices {

    @GET("api/character")
    Call<Characters> getCharacters();
}
