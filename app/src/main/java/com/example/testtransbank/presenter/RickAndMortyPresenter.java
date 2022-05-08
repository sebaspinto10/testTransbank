package com.example.testtransbank.presenter;

import androidx.annotation.NonNull;

import com.example.testtransbank.base.BasePresenter;
import com.example.testtransbank.model.Info;
import com.example.testtransbank.model.Results;
import com.example.testtransbank.model.RickAndMortyInteractor;
import com.example.testtransbank.view.RickAndMortyCharacters;

import java.util.ArrayList;

public class RickAndMortyPresenter extends BasePresenter implements RickAndMortyInteractor.onDetailsFetched {

    private final RickAndMortyCharacters view;
    private final RickAndMortyInteractor interactor;
    public boolean aptoParaCargar;

    public RickAndMortyPresenter(@NonNull RickAndMortyCharacters view, RickAndMortyInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void fetchData(String query, boolean addCharacter) {
        if (!addCharacter) {
            view.hideCharacters();
            view.showProgressBar();
        } else {
            if (query == null)
                return;
            view.showSpinKitView();
        }
        interactor.remoteFetch(this, query, addCharacter);
    }


    @Override
    public void onSuccess(ArrayList<Results> results, Info info) {
        aptoParaCargar = true;
        if (info.getPrev() == null) {
            view.hideProgressBar();
            view.showCharacters();
            view.showCharactersDetails(results, info.getNext(), false);
        } else {
            view.hideSpinKitViewr();
            view.showCharactersDetails(results, info.getNext(), true);
        }

    }

    @Override
    public void onFailure() {
        aptoParaCargar = true;
        view.hideProgressBar();
        view.showDateFetchError();
    }

    @Override
    public void noResults() {
        aptoParaCargar = true;
        view.hideProgressBar();
        view.showNoResults();
    }
}
