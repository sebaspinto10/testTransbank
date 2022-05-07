package com.example.testtransbank.Interface;

import com.example.testtransbank.model.Results;

import java.util.ArrayList;

public interface RickAndMortyView {

    void showProgressBar();

    void hideProgressBar();

    void showCharacters();

    void hideCharacters();

    void showCharactersDetails(ArrayList<Results> results);

    void fetchCharacters();

    void showDateFetchError();

    void showNoResults();

    void reloadData();
}
