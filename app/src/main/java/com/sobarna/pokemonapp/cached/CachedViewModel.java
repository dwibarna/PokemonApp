package com.sobarna.pokemonapp.cached;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sobarna.pokemonapp.data.local.PokemonCatch;
import com.sobarna.pokemonapp.data.local.PokemonRepository;

import java.util.List;

public class CachedViewModel extends ViewModel {

    private final PokemonRepository pokemonRepository;

    public CachedViewModel(Application application){
        pokemonRepository = new PokemonRepository(application);
    }
    public LiveData<List<PokemonCatch>> allPokemonCatch(){
        return pokemonRepository.allPokemonCatch();
    }
}
