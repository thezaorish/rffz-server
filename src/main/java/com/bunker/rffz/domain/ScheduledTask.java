package com.bunker.rffz.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "scheduled_task")
public class ScheduledTask implements Serializable {
	private static final long serialVersionUID = -3506836432887719260L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private Name name;

	private boolean active;

	@Column(name = "last_run")
	private Date lastRun;

	public ScheduledTask() {
		//
	}
	public ScheduledTask(Name name, boolean active) {
		this.name = name;
		this.active = active;
	}

	public enum Name {
		CandidatesRetrievalJob,
		CandidatesAnalyserJob
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getLastRun() {
		return lastRun;
	}
	public void setLastRun(Date lastRun) {
		this.lastRun = lastRun;
	}

}
