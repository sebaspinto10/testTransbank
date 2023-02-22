package com.example.testtransbank.model;

import android.util.Log;

import com.example.testtransbank.Interface.RickAndMortyApiServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RickAndMortyInteractor {

    private static final String TAG = "RickAndMortyInteractor";
    private List<Results> listCharacters;

    public interface onDetailsFetched {

        void onSuccess(ArrayList<Results> results, Info info);
        void onFailure();
        void noResults();

    }

    public void remoteFetch(final onDetailsFetched listener, final String query, final boolean addCharacter) {

        final String BASE_URL = "https://rickandmortyapi.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RickAndMortyApiServices services = retrofit.create(RickAndMortyApiServices.class);
        Call<Characters> charactersCall;
        if (addCharacter) {
            charactersCall = services.getCharacters(query);
        } else {
            charactersCall = services.getCharacterbyQuery(query);
        }
        charactersCall.enqueue(new Callback<Characters>() {
                    @Override
                    public void onResponse(Call<Characters> call, Response<Characters> response) {
                        if (!response.isSuccessful()) {
                            listener.onFailure();
                            return;
                        }

                        Characters contenido = response.body();

                        if (contenido != null) {
                            List<Results> listCharacters = contenido.getResults();
                            if (!listCharacters.isEmpty()) {
                                Log.i(TAG, "onSuccess");
                                listener.onSuccess((ArrayList<Results>) listCharacters, contenido.getInfo());
                            } else {
                                Log.e(TAG, "noResults");
                                listener.noResults();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Characters> call, Throwable t) {
                        listener.onFailure();
                        Log.e(TAG, "onFailure: "+t.getMessage());
                    }
                });

    }
}
