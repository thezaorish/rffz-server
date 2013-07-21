package com.bunker.rffz.domain.feedarticle.dto;

import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.bunker.rffz.domain.feedarticle.Article;
import com.bunker.rffz.domain.feedarticle.Candidate;

@XmlRootElement(name = "feedItem")
@XmlAccessorType(XmlAccessType.NONE)
public class FeedItem {

	private static final String[] EXCLUDED_FIELDS = new String[] {"creationDate", "publishDate"};

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");

	@XmlElement
	private String title;

	@XmlElement
	private String description;

	@XmlElement
	private String imagePath;

	@XmlElement
	private String publishDate;

	@XmlElement
	private String creationDate;

	@XmlElement
	private String url;

	@XmlElement
	private String source;

	public FeedItem() {
		// INFO needed by JAX-B
	}
	public FeedItem(Article article) {
		Candidate candidate = article.getCandidate();

		title = candidate.getTitle();
		description = candidate.getDescription();
		imagePath = candidate.getImagePath();
		publishDate = dateFormat.format(candidate.getPublishDate());
		creationDate = dateFormat.format(article.getCreationDate());
		url = candidate.getUrl();
		source = candidate.getFeedSource().getName();
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

	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, EXCLUDED_FIELDS);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, EXCLUDED_FIELDS);
	}
	
	@Override
	public String toString() {
		return "FeedItem [title=" + title + ", description=" + description + ", imagePath=" + imagePath + ", publishDate=" + publishDate + ", creationDate=" + creationDate + ", url=" + url + ", source=" + source + "]";
	}

}
