package com.sobarna.pokemonapp.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sobarna.pokemonapp.R;
import com.sobarna.pokemonapp.data.local.PokemonCatch;
import com.sobarna.pokemonapp.data.local.ViewModelFactory;

public class DetailActivity extends AppCompatActivity {

    private FloatingActionButton fab ;
    private FloatingActionButton fabDlt ;
    private TextView tvStateBall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        fabDlt = findViewById(R.id.fabDelete);
        fab = findViewById(R.id.fabPokeball);

        DetailViewModel detailViewModel = obtainViewModel(DetailActivity.this);

        tvStateBall = findViewById(R.id.tvStateBall);

        String namePokemon = getIntent().getStringExtra("pokemon_data");
        TextView textView = findViewById(R.id.tvDetailName);

        textView.setText(namePokemon);

        ImageView imageView = findViewById(R.id.ivDetailPokemon);
        TextView  tvHp = findViewById(R.id.tvDetailHp);
        TextView tvAttack = findViewById(R.id.tvDetailAttack);
        TextView tvDef = findViewById(R.id.tvDetailDef);
        TextView tvSpd = findViewById(R.id.tvDetailSpd);
        TextView tvSpecialAtk = findViewById(R.id.tvSpecialAtk);
        TextView tvSpecialDef = findViewById(R.id.tvSpecialDef);

        TextView moves1 = findViewById(R.id.tvDetailMoves1);
        TextView moves2 = findViewById(R.id.tvDetailMoves2);
        TextView moves3 = findViewById(R.id.tvDetailMoves3);
        TextView moves4 = findViewById(R.id.tvDetailMoves4);

        detailViewModel.getApi(namePokemon);
        detailViewModel.detailResponseLiveData().observe(this, pokemonDetailResponse -> {

            if (pokemonDetailResponse !=null){
                Glide.with(DetailActivity.this)
                       .load(pokemonDetailResponse.getSprites().getBackDefault())
                        .into(imageView);

                StringBuilder stringHp = new StringBuilder();
                StringBuilder stringAttack = new StringBuilder();
                StringBuilder stringDef = new StringBuilder();
                StringBuilder stringSpd = new StringBuilder();
                StringBuilder stringSpcAtt = new StringBuilder();
                StringBuilder stringSpcDef = new StringBuilder();

                tvHp.setText(stringHp.append(pokemonDetailResponse.getStats().get(0).getBaseStat()));
                tvAttack.setText(stringAttack.append(pokemonDetailResponse.getStats().get(1).getBaseStat()));
                tvDef.setText(stringDef.append(pokemonDetailResponse.getStats().get(2).getBaseStat()));
                tvSpecialAtk.setText(stringSpcAtt.append(pokemonDetailResponse.getStats().get(3).getBaseStat()));
                tvSpecialDef.setText(stringSpcDef.append(pokemonDetailResponse.getStats().get(4).getBaseStat()));
                tvSpd.setText(stringSpd.append(pokemonDetailResponse.getStats().get(5).getBaseStat()));

                if (pokemonDetailResponse.getMoves().size() > 1){
                    moves1.setText(pokemonDetailResponse.getMoves().get(1).getMove().getName());
                    moves2.setText(pokemonDetailResponse.getMoves().get(2).getMove().getName());
                    moves3.setText(pokemonDetailResponse.getMoves().get(3).getMove().getName());
                    moves4.setText(pokemonDetailResponse.getMoves().get(4).getMove().getName());
                }else if (pokemonDetailResponse.getMoves().size() == 1){
                    moves2.setText(R.string.empty);
                    moves3.setText(R.string.empty);
                    moves4.setText(R.string.empty);
                }


                int number = detailViewModel.checkPokemonCatch(pokemonDetailResponse.getId());
                Log.d(this.toString(),String.valueOf(number));
                statusOfPokemon(number > 0);

                fab.setOnClickListener(view -> {
                    if(Math.random() <=0.5){
                        final EditText popUpName = new EditText(DetailActivity.this);
                        AlertDialog log = new AlertDialog.Builder(DetailActivity.this)
                                .setCancelable(false)
                                .setMessage("Give Your Pokemon Name")
                                .setView(popUpName)
                                .setPositiveButton("SAVE", (dialog, which) -> {
                                    String ok = String.valueOf(popUpName.getText());
                                    //    tAlert.setText(ok);
                                    textView.setText(ok);
                                    PokemonCatch pokemonCatch = new PokemonCatch();
                                    pokemonCatch.setId(pokemonDetailResponse.getId());
                                    pokemonCatch.setState(number);
                                    pokemonCatch.setBackDefault(pokemonDetailResponse.getSprites().getBackDefault());
                                    pokemonCatch.setName(pokemonDetailResponse.getName());
                                    pokemonCatch.setNamePet(String.valueOf(popUpName.getText()));
                                    detailViewModel.insertPokemonCatch(pokemonCatch);
                                })
                                .create();
                        log.show();

                        statusOfPokemon(true);
                    }
                });

                fabDlt.setOnClickListener(view -> {
                    detailViewModel.deletePokemon(pokemonDetailResponse.getId());
                    statusOfPokemon(false);
                });
            }
       });


    }
    @NonNull
    private static DetailViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(DetailViewModel.class);
    }
    public void statusOfPokemon(Boolean state){
        if(state){
            fab.setVisibility(View.INVISIBLE);
            fabDlt.setVisibility(View.VISIBLE);
            tvStateBall.setText(getString(R.string.release));
        }else{
            tvStateBall.setText(getString(R.string.catch_pokemon));
            fab.setVisibility(View.VISIBLE);
            fabDlt.setVisibility(View.INVISIBLE);
        }
    }

}