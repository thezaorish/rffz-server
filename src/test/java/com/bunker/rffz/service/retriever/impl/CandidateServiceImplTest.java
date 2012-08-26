package com.bunker.rffz.service.retriever.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bunker.rffz.dao.retriever.CandidateDao;
import com.bunker.rffz.domain.retriever.Candidate;
import com.bunker.rffz.domain.retriever.FeedSource;
import com.bunker.rffz.service.retriever.CandidateService;

@RunWith(MockitoJUnitRunner.class)
public class CandidateServiceImplTest {

	private CandidateService candidateService;

	@Mock
	private CandidateDao candidateDao;

	@Before
	public void setUp() {
		candidateService = new CandidateServiceImpl(candidateDao);
	}

	@Test
	public void shouldSaveCandidate() {
		// given a candidate
		Candidate candidate = new Candidate();

		// when
		candidateService.updateCandidate(candidate);

		// then
		verify(candidateDao).save(candidate);
	}

	@Test
	public void shouldGetUnprocessedCandidates() {
		// given
		Candidate candidate = new Candidate();
		List<Candidate> candidates = new ArrayList<Candidate>(Arrays.asList(candidate));
		given(candidateDao.getUnprocessedCandidates()).willReturn(candidates);

		// when
		List<Candidate> returnedCandidates = candidateService.getUnprocessedCandidates();

		// then
		assertThat(returnedCandidates, is(candidates));
		verify(candidateDao).getUnprocessedCandidates();
	}

	@Test
	public void shouldCreateCandidate() {
		// given a new candidate
		FeedSource feedSource = new FeedSource("feedName", "feedUrl");
		Candidate candidate = new Candidate("title", "description", new Date(10), "link", feedSource);

		// NOT equal to any existing candidates
		given(candidateDao.countCandidates(candidate.getTitle(), feedSource)).willReturn(0);

		// when we try to create it
		candidateService.createCandidate(candidate);

		// then it should be saved
		verify(candidateDao).save(candidate);
	}
	@Test
	public void shouldNotCreateDuplicateCandidate() {
		// given a new candidate
		FeedSource feedSource = new FeedSource("feedName", "feedUrl");
		Candidate candidate = new Candidate("title", "description", new Date(10), "link", feedSource);

		// but equal to an existing candidate
		given(candidateDao.countCandidates(candidate.getTitle(), feedSource)).willReturn(1);

		// when we try to create it
		candidateService.createCandidate(candidate);

		// then it should NOT be saved
		verify(candidateDao, never()).save(any(Candidate.class));
	}

}
