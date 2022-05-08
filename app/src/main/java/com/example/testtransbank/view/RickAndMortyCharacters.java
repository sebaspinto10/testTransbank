package com.example.testtransbank.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testtransbank.Interface.RickAndMortyView;
import com.example.testtransbank.R;
import com.example.testtransbank.base.BaseActivity;
import com.example.testtransbank.model.Results;
import com.example.testtransbank.model.RickAndMortyInteractor;
import com.example.testtransbank.model.adapter.AdapterCharacters;
import com.example.testtransbank.presenter.RickAndMortyPresenter;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;

public class RickAndMortyCharacters extends BaseActivity<RickAndMortyPresenter> implements RickAndMortyView, SearchView.OnQueryTextListener {


    RecyclerView rv;
    ProgressBar progress_barr;
    SpinKitView spinKitView;
    ImageView reload;
    TextView error;
    SearchView searchView;
    LinearLayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;
    AdapterCharacters adapterCharacters;

    @NonNull
    @Override
    protected RickAndMortyPresenter createPresenter(@NonNull Context context) {
        return new RickAndMortyPresenter(this, new RickAndMortyInteractor());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.Theme_TestTransbank);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rick_and_morty_characters);
        initializeUI();
        fetchCharacters("");
        searchView.setOnQueryTextListener(this);
        reload.setOnClickListener(v -> reloadData());
    }

    @Override
    public void initializeUI() {

        //RecyclerView
        rv = findViewById(R.id.rv_characters);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), layoutManager.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);

        progress_barr = findViewById(R.id.progressbar_loading);
        spinKitView = findViewById(R.id.spin_kit);
        reload = findViewById(R.id.imageview_reload);
        error = findViewById(R.id.textview_fetcherror);
        searchView = findViewById(R.id.searchview);
    }

    @Override
    public void showProgressBar() {
        progress_barr.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progress_barr.setVisibility(View.GONE);
    }

    @Override
    public void showSpinKitView() {
        spinKitView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSpinKitViewr() {
        spinKitView.setVisibility(View.GONE);
    }

    @Override
    public void showCharacters() {
        rv.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCharacters() {
        rv.setVisibility(View.GONE);
    }

    @Override
    public void showCharactersDetails(ArrayList<Results> results, String next, boolean addCharacters) {
        if (addCharacters) {
            adapterCharacters.addCharacters(results);
        } else {
            adapterCharacters = new AdapterCharacters(results, getApplicationContext());
            rv.setAdapter(adapterCharacters);
        }
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1) && dy > 0) {
                    if (mPresenter.aptoParaCargar) {
                        mPresenter.aptoParaCargar = false;
                        mPresenter.fetchData(next, true);
                    }
                }
            }
        });
    }

    @Override
    public void fetchCharacters(String query) {
        mPresenter.fetchData(query, false);
    }

    @Override
    public void showDateFetchError() {
        reload.setVisibility(View.VISIBLE);
        error.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoResults() {

    }

    @Override
    public void reloadData() {
        reload.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        fetchCharacters("");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        reload.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        mPresenter.fetchData(query, false);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        /*reload.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        mPresenter.fetchData(newText, false);*/
        return false;
    }
}