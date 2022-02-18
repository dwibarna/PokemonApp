package com.sobarna.pokemonapp.cached;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sobarna.pokemonapp.detail.DetailActivity;
import com.sobarna.pokemonapp.R;
import com.sobarna.pokemonapp.data.local.PokemonCatch;

import java.util.ArrayList;
import java.util.List;

public class CachedAdapter extends
        RecyclerView.Adapter<CachedAdapter.CachedViewHolder> {

    private final ArrayList<PokemonCatch> pokemonCatchArrayList;


    CachedAdapter(ArrayList<PokemonCatch> pokemonCatchArrayList){
        this.pokemonCatchArrayList = pokemonCatchArrayList;
    }
    public void  pokemonAdd(List<PokemonCatch> pokemonCatchList){
        pokemonCatchArrayList.clear();
        pokemonCatchArrayList.addAll(pokemonCatchList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CachedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pokemon,parent,false);

        return new CachedViewHolder(view);

//        return new PokemonAdapter.PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CachedViewHolder holder, int position) {
        PokemonCatch pokemonCatch = pokemonCatchArrayList.get(position);

        if(pokemonCatch!=null){

            Glide.with(holder.itemView)
                    .load(pokemonCatch.getBackDefault())
                    .into(holder.ivPokemon);
            holder.tvPokemon.setText(pokemonCatch.getNamePet());

            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class)
                        .putExtra("pokemon_data",pokemonCatch.getName());
                holder.itemView.getContext().startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return pokemonCatchArrayList.size();
    }


    public static class CachedViewHolder extends RecyclerView.ViewHolder {
        TextView tvPokemon;
        ImageView ivPokemon;

        public CachedViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPokemon = itemView.findViewById(R.id.tvNamePokemon);
            ivPokemon = itemView.findViewById(R.id.ivPokemon);
        }
    }
}
