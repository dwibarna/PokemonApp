package com.sobarna.pokemonapp.detail;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sobarna.pokemonapp.data.remote.ApiConfig;
import com.sobarna.pokemonapp.data.response.PokemonDetailResponse;
import com.sobarna.pokemonapp.data.local.PokemonCatch;
import com.sobarna.pokemonapp.data.local.PokemonRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends ViewModel {

    private final PokemonRepository pokemonRepository;
    MutableLiveData<PokemonDetailResponse> responseMutableLiveData;

    public LiveData<PokemonDetailResponse> detailResponseLiveData(){
        return responseMutableLiveData;

    }
    public DetailViewModel(Application application){
        responseMutableLiveData = new MutableLiveData<>();
        pokemonRepository = new PokemonRepository(application);
    }

    public void insertPokemonCatch(PokemonCatch pokemonCatch){
        pokemonRepository.insertPokemonCatch(pokemonCatch);
    }
    public int checkPokemonCatch(int id){
       return pokemonRepository.checkPokemonCatch(id);
    }
    public void deletePokemon(int id){
        pokemonRepository.deletePokemon(id);
    }



    public void getApi (String name){
        ApiConfig.retrofitInstance().getDetailPokemon(name).enqueue(new Callback<PokemonDetailResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonDetailResponse> call, @NonNull Response<PokemonDetailResponse> response) {

                if (response.body() !=null){
                    responseMutableLiveData.postValue(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<PokemonDetailResponse> call, @NonNull Throwable t) {
                Log.d(this.toString(),t.getMessage());
            }
        });
    }

}
