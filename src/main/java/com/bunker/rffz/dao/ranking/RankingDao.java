package com.bunker.rffz.dao.ranking;

import java.util.List;

import com.bunker.rffz.domain.ranking.Ranking;
import com.bunker.rffz.domain.ranking.RankingType;

public interface RankingDao {

	List<Ranking> getByType(RankingType type);
	
	void save(Ranking ranking);
	
}
