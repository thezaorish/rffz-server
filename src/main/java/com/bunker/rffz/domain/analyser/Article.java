package com.bunker.rffz.domain.analyser;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bunker.rffz.domain.retriever.Candidate;

/**
 * Encapsulates the model for an article
 * @author zaorish
 */
@Entity
@Table(name = "article")
public class Article implements Serializable {
	private static final long serialVersionUID = 1689817542414580519L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false)
	private Date creationDate;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Candidate candidate;

	// TODO List<ArticleType> (enum: Steaua, Rapid ...)

	public Article() {
		// INFO needed by hibernate
	}
	public Article(Candidate candidate) {
		this.candidate = candidate;
		this.creationDate = new Date();
	}

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

	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

}
