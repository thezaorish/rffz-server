package com.bunker.rffz.service.ranking;

import com.bunker.rffz.domain.ranking.dto.RankingList;

public interface RankingService {

	RankingList getNationalLeagueRanking();
	
	RankingList getEuropeanLeagueRanking();
	
}
