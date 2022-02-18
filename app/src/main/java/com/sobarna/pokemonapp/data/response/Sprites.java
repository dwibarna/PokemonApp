package com.sobarna.pokemonapp.data.response;

import com.google.gson.annotations.SerializedName;

public class Sprites{



	@SerializedName("back_default")
	private final String backDefault;

	public Sprites(String backDefault) {
		this.backDefault = backDefault;
	}


	public String getBackDefault(){
		return backDefault;
	}
}