package com.sobarna.pokemonapp.data.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PokemonListResponse{

	@SerializedName("next")
	private final String next;

	@SerializedName("previous")
	private final String previous;

	@SerializedName("count")
	private final Integer count;

	@SerializedName("results")
	private final List<ResultsItem> results;

	public PokemonListResponse(String next, String previous, Integer count, List<ResultsItem> results) {
		this.next = next;
		this.previous = previous;
		this.count = count;
		this.results = results;
	}

	public String getNext(){
		return next;
	}

	public String getPrevious(){
		return previous;
	}

	public Integer getCount(){
		return count;
	}

	public List<ResultsItem> getResults(){
		return results;
	}
}