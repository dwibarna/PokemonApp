package com.sobarna.pokemonapp.data.response;

import com.google.gson.annotations.SerializedName;

public class StatsItem{

	@SerializedName("base_stat")
	private final int baseStat;

	public StatsItem(int baseStat) {
		this.baseStat = baseStat;
	}


	public int getBaseStat(){
		return baseStat;
	}

}