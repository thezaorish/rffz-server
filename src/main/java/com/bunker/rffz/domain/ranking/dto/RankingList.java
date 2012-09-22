package com.bunker.rffz.domain.ranking.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bunker.rffz.domain.ranking.Ranking;

@XmlRootElement(name = "rankingList")
@XmlAccessorType(XmlAccessType.NONE)
public class RankingList {

	@XmlElement
	private List<Ranking> rankings;

	public RankingList() {
		//
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
	
}
