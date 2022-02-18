package com.sobarna.pokemonapp.cached;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sobarna.pokemonapp.R;
import com.sobarna.pokemonapp.data.local.PokemonCatch;
import com.sobarna.pokemonapp.data.local.ViewModelFactory;

import java.util.ArrayList;

public class CachedActivity extends AppCompatActivity {

    private CachedAdapter cachedAdapter;
    private final ArrayList<PokemonCatch> pokemonCatched = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catched);

        RecyclerView recyclerView = findViewById(R.id.rvCached);
        recyclerView.setHasFixedSize(true);

        if(getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }

        cachedAdapter = new CachedAdapter(pokemonCatched);
        recyclerView.setAdapter(cachedAdapter);

        CachedViewModel viewModel = obtainViewModel(CachedActivity.this);

        viewModel.allPokemonCatch().observe(this, pokemonCatches -> {
            if(pokemonCatches !=null){
                cachedAdapter.pokemonAdd(pokemonCatches);
            }
        });
    }
    @NonNull
    private static CachedViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(CachedViewModel.class);
    }
}