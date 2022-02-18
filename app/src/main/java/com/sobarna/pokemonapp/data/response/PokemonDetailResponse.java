package com.sobarna.pokemonapp.data.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PokemonDetailResponse{

	@SerializedName("sprites")
	private final Sprites sprites;

	@SerializedName("stats")
	private final List<StatsItem> stats;

	@SerializedName("moves")
	private final List<MovesItem> moves;

	@SerializedName("name")
	private final String name;

	@SerializedName("id")
	private final int id;

	public PokemonDetailResponse(Sprites sprites, List<StatsItem> stats, List<MovesItem> moves, String name, int id) {
		this.sprites = sprites;
		this.stats = stats;
		this.moves = moves;
		this.name = name;
		this.id = id;
	}

	public Sprites getSprites(){
		return sprites;
	}

	public List<StatsItem> getStats(){
		return stats;
	}

	public List<MovesItem> getMoves(){
		return moves;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

}