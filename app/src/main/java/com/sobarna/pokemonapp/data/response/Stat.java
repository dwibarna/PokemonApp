package com.sobarna.pokemonapp.data.response;

import com.google.gson.annotations.SerializedName;

public class Stat{

	@SerializedName("name")
	private String name;

	@SerializedName("url")
	private String url;

	public String getName(){
		return name;
	}

	public String getUrl(){
		return url;
	}
}