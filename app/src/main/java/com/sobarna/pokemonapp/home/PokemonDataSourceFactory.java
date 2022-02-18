package com.sobarna.pokemonapp.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.sobarna.pokemonapp.data.response.ResultsItem;

public class PokemonDataSourceFactory extends DataSource.Factory {

    private final MutableLiveData<PageKeyedDataSource<Integer,ResultsItem>> liveData = new MutableLiveData<>();

    public MutableLiveData<PageKeyedDataSource<Integer, ResultsItem>> getItemLiveDataSource() {
        return liveData;
    }

    @NonNull
    @Override
    public DataSource<Integer, ResultsItem> create() {

        PokemonDataSource pokemonDataSource = new PokemonDataSource();

        liveData.postValue(pokemonDataSource);

        return pokemonDataSource;
    }
}
