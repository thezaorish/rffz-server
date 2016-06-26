package com.thezaorish;

import javax.persistence.*;

/**
 * Created by zaorish on 26/06/16.
 */
@Entity
@Table(name = "feed_source")
public class FeedSource {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@Column(unique = true, nullable = false)
	private String url;

	private boolean active;

	public FeedSource() {
		// needed by hibernate
	}
	public FeedSource(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

}
