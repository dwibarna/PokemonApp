package com.sobarna.pokemonapp.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sobarna.pokemonapp.R;
import com.sobarna.pokemonapp.data.response.ResultsItem;
import com.sobarna.pokemonapp.detail.DetailActivity;


public class PokemonAdapter extends PagedListAdapter
        <ResultsItem, PokemonAdapter.PokemonViewHolder> {


    PokemonAdapter(){
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pokemon,parent,false);

        return new PokemonViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        ResultsItem pokemon = getItem(position);

        if(pokemon != null){
            String[] fromUrl = pokemon.getUrl().split("/");
            int getId = Integer.parseInt(fromUrl[fromUrl.length-1]);
            String fullLink = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/"+getId+".png";


            Glide.with(holder.itemView)
                    .load(fullLink)
                    .into(holder.ivPokemon);
            holder.tvPokemon.setText(pokemon.getName());

            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), DetailActivity.class)
                        .putExtra("pokemon_data",pokemon.getName());
                view.getContext().startActivity(intent);
            });
        }
    }

    private static DiffUtil.ItemCallback<ResultsItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ResultsItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull ResultsItem oldItem, @NonNull ResultsItem newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull ResultsItem oldItem, @NonNull ResultsItem newItem) {
                    return oldItem == newItem;
                }
            };

    static class PokemonViewHolder extends RecyclerView.ViewHolder {

        TextView tvPokemon;
        ImageView ivPokemon;

         public PokemonViewHolder(@NonNull View itemView) {
             super(itemView);
             tvPokemon = itemView.findViewById(R.id.tvNamePokemon);
             ivPokemon = itemView.findViewById(R.id.ivPokemon);
         }
     }
}

