package com.bunker.rffz.service.analyser.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bunker.rffz.domain.feedarticle.Candidate;
import com.bunker.rffz.domain.feedarticle.FeedSource;
import com.bunker.rffz.domain.util.ScheduledTask.Name;
import com.bunker.rffz.service.feedarticle.ArticleService;
import com.bunker.rffz.service.feedarticle.CandidateAnalyserService;
import com.bunker.rffz.service.feedarticle.CandidateService;
import com.bunker.rffz.service.feedarticle.impl.CandidateAnalyserServiceImpl;
import com.bunker.rffz.service.job.ScheduledTaskService;

@RunWith(MockitoJUnitRunner.class)
public class CandidateAnalyserServiceImplTest {

	private CandidateAnalyserService candidateAnalyserService;

	@Mock
	private CandidateService candidateService;
	@Mock
	private ArticleService articleService;
	@Mock
	private ScheduledTaskService scheduledTaskService;

	@Before
	public void setUp() {
		this.candidateAnalyserService = new CandidateAnalyserServiceImpl(candidateService, articleService, scheduledTaskService);
	}

	@Test
	public void shouldDoNothingWhenNoUnprocessedCandidates() {
		// given there are no unprocessed candidates
		given(candidateService.getUnprocessedCandidates()).willReturn(Collections.EMPTY_LIST);

		// when
		candidateAnalyserService.analyseCandidates();

		// then no action should be taken
		verify(articleService, never()).createArticle(any(Candidate.class));
		verify(candidateService, never()).updateCandidate(any(Candidate.class));
		//
		verify(scheduledTaskService).updateLastRun(Name.CandidatesAnalyserJob);
	}

	@Test
	public void shouldHandleUnprocessedValidCandidates() {
		// given an unprocessed steaua valid candidate
		FeedSource feedSource = new FeedSource("name", "url");
		Candidate candidate = new Candidate("Steaua", "description", new Date(1000), "link", feedSource);
		List<Candidate> candidates = new ArrayList<Candidate>(Arrays.asList(candidate));
		given(candidateService.getUnprocessedCandidates()).willReturn(candidates);

		// when
		candidateAnalyserService.analyseCandidates();

		// then an article must be created for every valid candidate
		verify(articleService).createArticle(candidate);
		// and the candidate processed flag must be updated
		assertThat(candidate.isProcessed(), is(true));
		verify(candidateService).updateCandidate(candidate);
		//
		verify(scheduledTaskService).updateLastRun(Name.CandidatesAnalyserJob);
	}
	@Test
	public void shouldHandleUnprocessedInvalidCandidates() {
		// given an unprocessed steaua invalid candidate
		FeedSource feedSource = new FeedSource("name", "url");
		Candidate candidate = new Candidate("name", "description", new Date(1000), "link", feedSource);
		List<Candidate> candidates = new ArrayList<Candidate>(Arrays.asList(candidate));
		given(candidateService.getUnprocessedCandidates()).willReturn(candidates);

		// when
		candidateAnalyserService.analyseCandidates();

		// then NO article must be created
		verify(articleService, never()).createArticle(any(Candidate.class));
		// and the candidate processed flag must be updated
		assertThat(candidate.isProcessed(), is(true));
		verify(candidateService).updateCandidate(candidate);
		//
		verify(scheduledTaskService).updateLastRun(Name.CandidatesAnalyserJob);
	}

}
