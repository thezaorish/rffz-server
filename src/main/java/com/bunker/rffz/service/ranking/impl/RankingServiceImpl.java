package com.bunker.rffz.service.ranking.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bunker.rffz.dao.ranking.RankingDao;
import com.bunker.rffz.domain.ranking.RankingType;
import com.bunker.rffz.domain.ranking.dto.RankingList;
import com.bunker.rffz.service.ranking.RankingService;

@Service("rankingService")
@Transactional
public class RankingServiceImpl implements RankingService {

	private RankingDao rankingDao;
	
	@Autowired
	public RankingServiceImpl(RankingDao rankingDao) {
		this.rankingDao = rankingDao;
	}
	
	@Override
	public RankingList getNationalLeagueRanking() {
		return new RankingList(rankingDao.getByType(RankingType.NationalLeague));
	}

	@Override
	public RankingList getEuropeanLeagueRanking() {
		return new RankingList(rankingDao.getByType(RankingType.EuropeanLeague));
	}

}
