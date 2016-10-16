package com.thezaorish;


import java.util.UUID;

import static java.util.UUID.randomUUID;

/**
 * Created by zaorish on 28/06/16.
 */
public class Article {

	private UUID id = randomUUID();

	private String title;

	private String description;

	private String source;

	private long publishDate;

	private String imagePath;

	private String url;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	public long getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(long publishDate) {
		this.publishDate = publishDate;
	}

	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Article{" +
				"title='" + title + '\'' +
				", description='" + description + '\'' +
				", source='" + source + '\'' +
				", publishDate='" + publishDate + '\'' +
				", imagePath='" + imagePath + '\'' +
				", url='" + url + '\'' +
				'}';
	}

}
