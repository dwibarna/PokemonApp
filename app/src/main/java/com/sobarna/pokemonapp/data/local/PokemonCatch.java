package com.sobarna.pokemonapp.data.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "pokemon")
public class PokemonCatch {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "state")
    private int state;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;


    @ColumnInfo(name = "namePet")
    private String namePet;


    @SerializedName("back_default")
    @ColumnInfo(name = "back_default")
    private String backDefault;

    public String getBackDefault(){
        return backDefault;
    }

    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }


    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNamePet(String namePet) {
        this.namePet = namePet;
    }

    public String getNamePet() {
        return namePet;
    }

    public void setBackDefault(String backDefault) {
        this.backDefault = backDefault;
    }

    public String getName(){
        return name;
    }



}
