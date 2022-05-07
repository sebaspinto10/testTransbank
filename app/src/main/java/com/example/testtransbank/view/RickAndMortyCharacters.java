package com.example.testtransbank.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.testtransbank.Interface.RickAndMortyView;
import com.example.testtransbank.R;
import com.example.testtransbank.base.BaseActivity;
import com.example.testtransbank.base.BasePresenter;
import com.example.testtransbank.model.Results;
import com.example.testtransbank.model.RickAndMortyInteractor;
import com.example.testtransbank.presenter.RickAndMortyPresenter;

import java.util.ArrayList;

public class RickAndMortyCharacters extends BaseActivity<RickAndMortyPresenter> implements RickAndMortyView {

    TextView cantPersonajes;

    @NonNull
    @Override
    protected RickAndMortyPresenter createPresenter(@NonNull Context context) {
        return new RickAndMortyPresenter(this, new RickAndMortyInteractor());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rick_and_morty_characters);
        cantPersonajes = findViewById(R.id.cant_personajes);
        fetchCharacters();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showCharacters() {

    }

    @Override
    public void hideCharacters() {

    }

    @Override
    public void showCharactersDetails(ArrayList<Results> results) {
        cantPersonajes.setText("cantidad de personajes: " +results.size());
    }

    @Override
    public void fetchCharacters() {
        mPresenter.fetchData();
    }

    @Override
    public void showDateFetchError() {

    }

    @Override
    public void showNoResults() {

    }

    @Override
    public void reloadData() {

    }
}