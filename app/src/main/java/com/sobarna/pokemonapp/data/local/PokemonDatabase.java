package com.sobarna.pokemonapp.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PokemonCatch.class},version = 1,exportSchema = false)
public abstract class PokemonDatabase extends RoomDatabase {

    private static volatile PokemonDatabase INSTANCE;

    public static PokemonDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (PokemonDatabase.class){
                INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        PokemonDatabase.class,
                        "pokemon_db"
                ).allowMainThreadQueries()
                        .build();
            }
        }
        return INSTANCE;
    }
    public abstract PokemonDao pokemonDao();
}
