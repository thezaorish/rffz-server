package com.bunker.rffz.domain.feedarticle;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Encapsulates the rss feed source
 * @author zaorish
 */
@Entity
@Table(name = "feed_source")
public class FeedSource implements Serializable {
	private static final long serialVersionUID = 2800081936047296781L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@Column(unique = true, nullable = false)
	private String url;

	private boolean active;

	public FeedSource() {
		// INFO needed by hibernate
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
