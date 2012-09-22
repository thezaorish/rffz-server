package com.bunker.rffz.dao.ranking.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bunker.rffz.dao.ranking.RankingDao;
import com.bunker.rffz.domain.ranking.Ranking;
import com.bunker.rffz.domain.ranking.RankingType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/unitTest-context.xml")
@Transactional
public class HibernateRankingDaoTest {

	@Autowired
	private RankingDao rankingDao;
	
	@Test
	public void shouldGetRankingsOnlyByTheRequeiredType() {
		// given
		Ranking natinalRanking = new Ranking();
		natinalRanking.setType(RankingType.NationalLeague);
		rankingDao.save(natinalRanking);
		
		Ranking europeanRanking = new Ranking();
		europeanRanking.setType(RankingType.EuropeanLeague);
		rankingDao.save(europeanRanking);
		
		// when
		List<Ranking> retrievedRankings = rankingDao.getByType(RankingType.NationalLeague);
		
		// then
		assertThat(retrievedRankings.size(), is(1));
		assertThat(retrievedRankings.get(0), is(natinalRanking));
	}
	
	@Test
	public void shouldGetRankingsOrderedByRank() {
		// given
		Ranking lowRanked = new Ranking();
		lowRanked.setType(RankingType.NationalLeague);
		lowRanked.setRank(100);
		rankingDao.save(lowRanked);
		
		Ranking highRanked = new Ranking();
		highRanked.setType(RankingType.NationalLeague);
		highRanked.setRank(1);
		rankingDao.save(highRanked);
		
		// when
		List<Ranking> retrievedRankings = rankingDao.getByType(RankingType.NationalLeague);
		
		// then
		assertThat(retrievedRankings.size(), is(2));
		assertThat(retrievedRankings.get(0), is(highRanked));
		assertThat(retrievedRankings.get(1), is(lowRanked));
	}
	
}
