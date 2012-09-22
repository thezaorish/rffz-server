package com.bunker.rffz.dao.retriever.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bunker.rffz.dao.feedarticle.CandidateDao;
import com.bunker.rffz.dao.feedarticle.FeedSourceDao;
import com.bunker.rffz.domain.feedarticle.Candidate;
import com.bunker.rffz.domain.feedarticle.FeedSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/unitTest-context.xml")
@Transactional
public class HibernateCandidateDaoTest {

	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private FeedSourceDao feedSourceDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Candidate candidate;
	private FeedSource feedSource;

	@Before
	public void setUp() {
		feedSource = new FeedSource("name", "url");
		feedSource.setActive(true);
		feedSourceDao.save(feedSource);

		candidate = new Candidate("title", "description", new Date(), "link", feedSource);
	}

	@Test
	public void shouldSaveCandidate() {
		// given a configured candidate

		// and no candidates in db
		int count = jdbcTemplate.queryForInt("select count(*) from candidate");
		assertThat(count, is(0));

		// when
		candidateDao.save(candidate);

		// then the saved candidate should be in the db
		count = jdbcTemplate.queryForInt("select count(*) from candidate where title = ? and description = ?", candidate.getTitle(), candidate.getDescription());
		assertThat(count, is(1));
	}

	@Test
	public void shouldGetOnlyUnprocessedCandidates() {
		// given an unprocessed candidate
		candidateDao.save(candidate);

		// and a processed candidate
		Candidate processedCandidate = new Candidate("newtitle", "description", new Date(), "link", feedSource);
		processedCandidate.setProcessed(true);
		candidateDao.save(processedCandidate);

		// when
		List<Candidate> returnedCandidates = candidateDao.getUnprocessedCandidates();

		// then the returned candidate should be only the unprocessed one
		assertThat(returnedCandidates.size(), is(1));
		assertThat(returnedCandidates.get(0), is(candidate));
	}
	@Test
	public void shouldGetOnlyCandidatesFromActiveSources() {
		// given an unprocessed candidate
		candidateDao.save(candidate);

		// and an unprocessed candidate
		FeedSource inactiveSource = new FeedSource("another", "link");
		feedSourceDao.save(inactiveSource);
		Candidate invalidCandidate = new Candidate("newtitle", "description", new Date(), "link", inactiveSource);
		candidateDao.save(invalidCandidate);

		// when
		List<Candidate> returnedCandidates = candidateDao.getUnprocessedCandidates();

		// then the returned candidate should be only the unprocessed one
		assertThat(returnedCandidates.size(), is(1));
		assertThat(returnedCandidates.get(0), is(candidate));
	}

	@Test
	public void shouldOnlyCountCandidatesWithTitleAndSource() {
		// given a candidate with existing title and source
		candidateDao.save(candidate);
		Candidate newCandidate = new Candidate(candidate.getTitle(), "dghfud", new Date(10000), "url", feedSource);

		// when
		int count = candidateDao.countCandidates(newCandidate.getTitle(), newCandidate.getFeedSource());

		// then
		assertThat(count, is(1));
	}
	@Test
	public void shouldNotCountCandidatesWithTitleAndDifferentSource() {
		// given a candidate with existing title, but different source
		candidateDao.save(candidate);
		FeedSource newFeedSource = new FeedSource("newname", "newurl");
		feedSourceDao.save(newFeedSource);

		Candidate newCandidate = new Candidate("title", "dghfud", new Date(10000), "url", newFeedSource);

		// when
		int count = candidateDao.countCandidates(newCandidate.getTitle(), newCandidate.getFeedSource());

		// then
		assertThat(count, is(0));
	}
	@Test
	public void shouldNotCountCandidatesWithDifferentTitleAndSource() {
		// given a candidate with existing source, but different title
		candidateDao.save(candidate);
		Candidate newCandidate = new Candidate("newtitle", "dghfud", new Date(10000), "url", feedSource);

		// when
		int count = candidateDao.countCandidates(newCandidate.getTitle(), newCandidate.getFeedSource());

		// then
		assertThat(count, is(0));
	}
	@Test
	public void shouldNotCountCandidatesWithDifferentTitleAndDifferentSource() {
		// given a candidate with different title and source
		candidateDao.save(candidate);
		FeedSource newFeedSource = new FeedSource("newname", "newurl");
		feedSourceDao.save(newFeedSource);

		Candidate newCandidate = new Candidate("newtitle", "dghfud", new Date(10000), "url", newFeedSource);

		// when
		int count = candidateDao.countCandidates(newCandidate.getTitle(), newCandidate.getFeedSource());

		// then
		assertThat(count, is(0));
	}

}
