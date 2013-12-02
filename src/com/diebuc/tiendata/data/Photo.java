package com.diebuc.tiendata.data;

import java.util.List;

public class Photo {

	private String URL;
	private String description;
	private int favorites;
	private List<Comment> comments;

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	int getFavorites() {
		return favorites;
	}

	void setFavorites(int favorites) {
		this.favorites = favorites;
	}

}
