package com.sobarna.pokemonapp.data.response;

import com.google.gson.annotations.SerializedName;

public class MovesItem{

	@SerializedName("move")
	private Move move;

	public Move getMove(){
		return move;
	}
}