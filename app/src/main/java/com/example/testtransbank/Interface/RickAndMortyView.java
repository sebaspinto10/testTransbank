package com.example.testtransbank.Interface;

import com.example.testtransbank.model.Results;

import java.util.ArrayList;

public interface RickAndMortyView {

    void initializeUI();

    void showProgressBar();

    void hideProgressBar();

    void showSpinKitView();

    void hideSpinKitViewr();

    void showCharacters();

    void hideCharacters();

    void showCharactersDetails(ArrayList<Results> results, String next, boolean addCharacters);

    void fetchCharacters(String query);

    void showDateFetchError();

    void showNoResults();

    void reloadData();
}
