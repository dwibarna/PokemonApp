package com.sobarna.pokemonapp.home;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sobarna.pokemonapp.R;
import com.sobarna.pokemonapp.cached.CachedActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rvPokemon);

        if(getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }

        recyclerView.setHasFixedSize(true);

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        PokemonAdapter pokemonAdapter = new PokemonAdapter();

        mainViewModel.pokemonList.observe(this, pokemonAdapter::submitList);

        recyclerView.setAdapter(pokemonAdapter);

        Button button = findViewById(R.id.buttonPrev);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CachedActivity.class);
            startActivity(intent);
        });

    }
}