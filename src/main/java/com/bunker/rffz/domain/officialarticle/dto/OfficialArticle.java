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

@Entity
@Table(name = "official_article")
@XmlRootElement(name = "officialArticle")
@XmlAccessorType(XmlAccessType.NONE)
public class OfficialArticle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false)
	@XmlElement
	private Date creationDate;

	@Column(nullable = false)
	@XmlElement
	private String title;
	
	@Column(nullable = false)
	@Lob
	@XmlElement
	private String description;

	@Column(nullable = false)
	@Lob@
	XmlElement
	private String content;
	
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result
				+ ((published == null) ? 0 : published.hashCode());
		result = prime * result
				+ ((thumbnail == null) ? 0 : thumbnail.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfficialArticle other = (OfficialArticle) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (published == null) {
			if (other.published != null)
				return false;
		} else if (!published.equals(other.published))
			return false;
		if (thumbnail == null) {
			if (other.thumbnail != null)
				return false;
		} else if (!thumbnail.equals(other.thumbnail))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "OfficialArticle [id=" + id + ", creationDate=" + creationDate + ", title=" + title + ", description=" + description + ", content=" + content + ", thumbnail=" + thumbnail + ", image=" + image + ", published=" + published + "]";
	}
	
}
