package com.sobarna.pokemonapp.data.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PokemonRepository {
    private final PokemonDao mPokemonDao;
    private final ExecutorService executorService;

    public PokemonRepository(Application application){
        executorService = Executors.newSingleThreadExecutor();
        PokemonDatabase database = PokemonDatabase.getDatabase(application);
        mPokemonDao = database.pokemonDao();
    }
    public LiveData<List<PokemonCatch>> allPokemonCatch(){
        return mPokemonDao.getDataPokemonCatch();
    }

    public void insertPokemonCatch(PokemonCatch pokemonCatch){
        executorService.execute(() -> mPokemonDao.insert(pokemonCatch));
    }
    public int checkPokemonCatch(int id){
        return mPokemonDao.checkMyPokemon(id);
    }
    public void deletePokemon(int id){
        executorService.execute(() -> mPokemonDao.deletePokemon(id));
    }
}
