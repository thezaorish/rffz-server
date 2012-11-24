package com.bunker.rffz.domain.officialarticle.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "official_article")
@XmlRootElement(name = "officialArticle")
@XmlAccessorType(XmlAccessType.NONE)
public class OfficialArticle {

	private final static String[] EXCLUDED_FIELDS = new String[] {"creationDate"};
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false)
	private Date creationDate;

	@Column(nullable = false)
	@XmlElement
	private String title;
	
	@Column(nullable = false)
	@Lob
	@XmlElement
	private String description;

	@Column(nullable = false)
	@Lob
	@XmlElement
	private String content;
	
	@Column(nullable = false)
	@XmlElement
	private String url;
	
	@Column(nullable = false)
	@XmlElement
	private String thumbnail;
	
	@Column(nullable = false)
	@XmlElement
	private String image;
	
	@Column(nullable = false)
	@XmlElement
	private String published;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public OfficialArticle creationDate(Date creationDate) {
		setCreationDate(creationDate);
		return this;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public OfficialArticle title(String title) {
		setTitle(title);
		return this;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public OfficialArticle description(String description) {
		setDescription(description);
		return this;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public OfficialArticle content(String content) {
		setContent(content);
		return this;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public OfficialArticle url(String url) {
		this.url = url;
		return this;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public OfficialArticle thumbnail(String thumbnail) {
		setThumbnail(thumbnail);
		return this;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public OfficialArticle image(String image) {
		setImage(image);
		return this;
	}

	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public OfficialArticle published(String published) {
		setPublished(published);
		return this;
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
		return "OfficialArticle [id=" + id + ", creationDate=" + creationDate + ", title=" + title + ", description=" + description + ", content=" + content + ", thumbnail=" + thumbnail + ", image=" + image + ", published=" + published + "]";
	}
	
}
