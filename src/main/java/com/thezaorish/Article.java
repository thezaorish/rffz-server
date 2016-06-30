package com.thezaorish;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by zaorish on 28/06/16.
 */
public class Article extends ResourceSupport {

	private String title;

	private String description;

	private String source;

	private String publishDate;

	private String imagePath;

	private String url;

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

	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
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
