package com.sobarna.pokemonapp.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokemonDao {

    @Insert
    void insert(PokemonCatch pokemonCatch);

    @Query("SELECT * FROM pokemon")
    LiveData<List<PokemonCatch>> getDataPokemonCatch();

    @Query("DELETE FROM pokemon where id = :id")
    int deletePokemon(int id);

    @Query("SELECT count(*) FROM pokemon WHERE id = :id")
    int checkMyPokemon(int id);

}
