package com.thezaorish;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by zaorish on 28/06/16.
 */
public class Article extends ResourceSupport {

	private String title;

	private String url;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
				", url='" + url + '\'' +
				'}';
	}

}
