package com.bunker.rffz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bunker.rffz.domain.ranking.dto.RankingList;
import com.bunker.rffz.service.ranking.RankingService;

@Controller
@RequestMapping(value = "/ranking")
public class RankingController {

	private RankingService rankingService;

	@Autowired
	public RankingController(RankingService rankingService) {
		this.rankingService = rankingService;
	}
	
	@RequestMapping(value = "/steaua/national", method = RequestMethod.GET)
	@ResponseBody
	public RankingList getNationalLeagueRanking() {
		return rankingService.getNationalLeagueRanking();
	}
	
	@RequestMapping(value = "/steaua/european", method = RequestMethod.GET)
	@ResponseBody
	public RankingList getEuropeanLeagueRanking() {
		return rankingService.getEuropeanLeagueRanking();
	}
	
}
