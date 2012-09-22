package com.bunker.rffz.domain.ranking;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ranking")
@XmlRootElement(name = "ranking")
@XmlAccessorType(XmlAccessType.NONE)
public class Ranking  implements Serializable {
	private static final long serialVersionUID = 2256940693647567100L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RankingType type;
	
	@XmlElement
	private int rank;
	
	@XmlElement
	private String team;
	
	@XmlElement
	private int points;
	
	@Column(name = "games_played")
	@XmlElement(name = "played")
	private int gamesPlayed;
	
	@Column(name = "games_won")
	@XmlElement(name = "won")
	private int gamesWon;
	
	@Column(name = "games_tied")
	@XmlElement(name = "tied")
	private int gamesTied;
	
	@Column(name = "games_lost")
	@XmlElement(name = "lost")
	private int gamesLost;
	
	@Column(name = "goals_scored")
	@XmlElement(name = "scored")
	private int goalsScored;
	
	@Column(name = "goals_received")
	@XmlElement(name = "received")
	private int goalsReceived;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public RankingType getType() {
		return type;
	}
	public void setType(RankingType type) {
		this.type = type;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	
	public int getGamesWon() {
		return gamesWon;
	}
	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}
	
	public int getGamesTied() {
		return gamesTied;
	}
	public void setGamesTied(int gamesTied) {
		this.gamesTied = gamesTied;
	}
	
	public int getGamesLost() {
		return gamesLost;
	}
	public void setGamesLost(int gamesLost) {
		this.gamesLost = gamesLost;
	}
	
	public int getGoalsScored() {
		return goalsScored;
	}
	public void setGoalsScored(int goalsScored) {
		this.goalsScored = goalsScored;
	}
	
	public int getGoalsReceived() {
		return goalsReceived;
	}
	public void setGoalsReceived(int goalsReceived) {
		this.goalsReceived = goalsReceived;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + gamesLost;
		result = prime * result + gamesPlayed;
		result = prime * result + gamesTied;
		result = prime * result + gamesWon;
		result = prime * result + goalsReceived;
		result = prime * result + goalsScored;
		result = prime * result + points;
		result = prime * result + rank;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
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
		Ranking other = (Ranking) obj;
		if (gamesLost != other.gamesLost)
			return false;
		if (gamesPlayed != other.gamesPlayed)
			return false;
		if (gamesTied != other.gamesTied)
			return false;
		if (gamesWon != other.gamesWon)
			return false;
		if (goalsReceived != other.goalsReceived)
			return false;
		if (goalsScored != other.goalsScored)
			return false;
		if (points != other.points)
			return false;
		if (rank != other.rank)
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Ranking [type=" + type + ", rank=" + rank + ", team=" + team + ", points=" + points + ", gamesPlayed=" + gamesPlayed + ", gamesWon=" + gamesWon + ", gamesTied=" + gamesTied + ", gamesLost=" + gamesLost + ", goalsScored=" + goalsScored + ", goalsReceived=" + goalsReceived + "]";
	}
	
}
