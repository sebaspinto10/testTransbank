package com.example.testtransbank.presenter;

import androidx.annotation.NonNull;

import com.example.testtransbank.base.BasePresenter;
import com.example.testtransbank.model.Results;
import com.example.testtransbank.model.RickAndMortyInteractor;
import com.example.testtransbank.view.RickAndMortyCharacters;

import java.util.ArrayList;

public class RickAndMortyPresenter extends BasePresenter implements RickAndMortyInteractor.onDetailsFetched {

    private final RickAndMortyCharacters view;
    private final RickAndMortyInteractor interactor;

    public RickAndMortyPresenter (@NonNull RickAndMortyCharacters view, RickAndMortyInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void fetchData() {
        view.hideCharacters();
        view.showProgressBar();
        interactor.remoteFetch(this);
    }


    @Override
    public void onSuccess(ArrayList<Results> results) {
        view.hideProgressBar();
        view.showCharacters();
        view.showCharactersDetails(results);
    }

    @Override
    public void onFailure() {
        view.hideProgressBar();
        view.showDateFetchError();
    }

    @Override
    public void noResults() {
        view.hideProgressBar();
        view.showNoResults();
    }
}
