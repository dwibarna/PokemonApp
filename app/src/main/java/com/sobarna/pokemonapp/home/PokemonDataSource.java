package com.sobarna.pokemonapp.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.sobarna.pokemonapp.data.remote.ApiConfig;
import com.sobarna.pokemonapp.data.response.PokemonListResponse;
import com.sobarna.pokemonapp.data.response.ResultsItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDataSource extends PageKeyedDataSource<Integer, ResultsItem> {

    private static final int FIRST_PAGE = 0;

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> loadParams, @NonNull LoadCallback<Integer, ResultsItem> loadCallback) {
        ApiConfig.retrofitInstance().getPokemonList(loadParams.key).enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonListResponse> call, @NonNull Response<PokemonListResponse> response) {
                if(response.body() !=null){
                    if(response.body().getNext() !=null){
                        loadCallback.onResult(response.body().getResults(),loadParams.key + 4);

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<PokemonListResponse> call, @NonNull Throwable t) {
                Log.d(this.toString(),t.getMessage());
            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> loadParams, @NonNull LoadCallback<Integer, ResultsItem> loadCallback) {
        ApiConfig.retrofitInstance().getPokemonList(loadParams.key ).enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonListResponse> call, @NonNull Response<PokemonListResponse> response) {

                Integer adjacentKey = (loadParams.key >1 )? loadParams.key -4 : null;

                if (response.body()!=null){
                    loadCallback.onResult(response.body().getResults(),adjacentKey);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PokemonListResponse> call, @NonNull Throwable t) {
                Log.d(this.toString(),t.getMessage());
            }
        });
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> loadInitialParams, @NonNull LoadInitialCallback<Integer, ResultsItem> loadInitialCallback) {
        ApiConfig.retrofitInstance().getPokemonList(FIRST_PAGE).enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonListResponse> call, @NonNull Response<PokemonListResponse> response) {
                if(response.body() !=null){
                    loadInitialCallback.onResult(response.body().getResults(),null,FIRST_PAGE + 4);

                }

            }

            @Override
            public void onFailure(@NonNull Call<PokemonListResponse> call, @NonNull Throwable t) {
                Log.d(this.toString(),t.getMessage());
            }
        });
    }
}
