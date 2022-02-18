package com.sobarna.pokemonapp.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.sobarna.pokemonapp.data.response.ResultsItem;

public class MainViewModel extends ViewModel {

    LiveData<PagedList<ResultsItem>> pokemonList;
    LiveData<PageKeyedDataSource<Integer,ResultsItem>> livePage;

    public MainViewModel(){

        PokemonDataSourceFactory pokemonDataSourceFactory = new PokemonDataSourceFactory();

        livePage = pokemonDataSourceFactory.getItemLiveDataSource();

        PagedList.Config configPageList =
                (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(4)
                .build();

        pokemonList = (new LivePagedListBuilder(pokemonDataSourceFactory,configPageList)).build();
    }

}
