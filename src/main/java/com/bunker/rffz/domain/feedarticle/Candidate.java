package com.bunker.rffz.domain.feedarticle;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.google.common.collect.Sets;

/**
 * Encapsulates the model for an article candidate
 * @author zaorish
 */
@Entity
@Table(name = "candidate")
public class Candidate implements Serializable {
	private static final long serialVersionUID = 1689817542414580519L;

	private static final String IMG_REGEX = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
	private static final String A_REGEX = "<a[^>]+href\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>*>";

	private static final Set<String> STEAUA_DICTIONARY = Sets.newHashSet("steaua", "stelei", "stelist", "stelişti");
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	@Lob
	private String description;

	@Column(name = "image_path")
	@Lob
	private String imagePath;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "publish_date", nullable = false)
	private Date publishDate;

	@Column(nullable = false)
	private String url;

	private boolean processed;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@NotNull
	private FeedSource feedSource;

	public Candidate() {
		// INFO needed by hibernate
	}
	public Candidate(String title, String description, Date publishDate, String url, FeedSource feedSource) {
		this.title = title;
		this.description = description;
		this.publishDate = publishDate;
		this.url = url;
		this.feedSource = feedSource;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
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

	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isProcessed() {
		return processed;
	}
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public FeedSource getFeedSource() {
		return feedSource;
	}
	public void setFeedSource(FeedSource feedSource) {
		this.feedSource = feedSource;
	}

	public void extractImagePath() {
		Pattern p = Pattern.compile(IMG_REGEX);
		Matcher m = p.matcher(description);
		if (m.find()) {
			imagePath = m.group(1);
			description = description.replaceAll(IMG_REGEX, "").trim();
		}
	}
	public void cleanUpAnchor() {
		Pattern p = Pattern.compile(A_REGEX);
		Matcher m = p.matcher(description);
		if (m.find()) {
			description = description.replaceAll(A_REGEX, "").trim();
		}
	}
	public void cleanUpTags() {
		description = description.replaceAll("<p>", "").trim();
		description = description.replaceAll("</p>", "").trim();
		description = description.replaceAll("<a>", "").trim();
		description = description.replaceAll("</a>", "").trim();
	}

	public boolean isSteauaRelated() {
		return (isStringSteauaRelated(getTitle()) || isStringSteauaRelated(getDescription()));
	}
	private boolean isStringSteauaRelated(String string) {
		for (String str : STEAUA_DICTIONARY) {
			if (string.toLowerCase().contains(str)) {
				return true;
			}
		}
		return false;
	}

}