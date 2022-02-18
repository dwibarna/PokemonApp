package com.sobarna.pokemonapp.data.response;

import com.google.gson.annotations.SerializedName;

public class ResultsItem{

	@SerializedName("name")
	private String name;

	@SerializedName("url")
	private final String url;


	public ResultsItem(String url) {
		this.url = url;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getName(){
		return name;
	}

	public String getUrl(){
		return url;
	}
}