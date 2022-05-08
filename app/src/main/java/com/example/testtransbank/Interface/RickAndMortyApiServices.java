package com.example.testtransbank.Interface;

import com.example.testtransbank.model.Characters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RickAndMortyApiServices {

    /*@GET("api/character")
    Call<Characters> getCharacters();*/

    @GET("api/character/")
    Call<Characters> getCharacterbyQuery(@Query("name") String QUERY);

    @GET
    Call<Characters> getCharacters(@Url String url);
}
