package com.bunker.rffz.domain.ranking.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.bunker.rffz.domain.ranking.Ranking;

@XmlRootElement(name = "rankingList")
@XmlAccessorType(XmlAccessType.NONE)
public class RankingList {

	@XmlElement
	private List<Ranking> rankings;

	public RankingList() {
		// INFO needed by JAX-B
	}
	public RankingList(List<Ranking> rankings) {
		this.rankings = rankings;
	}
	
	public List<Ranking> getRankings() {
		return rankings;
	}
	public void setRankings(List<Ranking> rankings) {
		this.rankings = rankings;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public String toString() {
		return "RankingList [rankings=" + rankings + "]";
	}
	
}
