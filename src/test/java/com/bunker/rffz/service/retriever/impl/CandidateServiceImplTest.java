package com.bunker.rffz.service.retriever.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.bunker.rffz.config.PersistenceContextConfig;
import com.bunker.rffz.domain.retriever.Candidate;
import com.bunker.rffz.domain.retriever.FeedSource;
import com.bunker.rffz.repository.retriever.CandidateRepository;
import com.bunker.rffz.repository.retriever.FeedSourceRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceContextConfig.class }, loader = AnnotationConfigContextLoader.class)
public class CandidateServiceImplTest {
	
	@Autowired
	private CandidateServiceImpl candidateService;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private FeedSourceRepository feedSourceRepository;
	
	private Candidate candidate;
	private FeedSource feedSource;
	
	@Before
	public void setUp() {
		feedSource = new FeedSource("name", "url");
		feedSource.setActive(true);
		feedSourceRepository.save(feedSource);

		candidate = new Candidate("title", "description", new Date(), "link", feedSource);
	}
	
	@Test
	public void shouldGetAllUnprocessedCandidates() {
		// given an unprocessed candidate
		candidateRepository.save(candidate);

		// and a processed candidate
		Candidate processedCandidate = new Candidate("newtitle", "description", new Date(), "link", feedSource);
		processedCandidate.setProcessed(true);
		candidateRepository.save(processedCandidate);

		// when
		List<Candidate> returnedCandidates = candidateService.getUnprocessedCandidates();

		// then the returned candidate should be only the unprocessed one
		assertThat(returnedCandidates.size(), is(1));
		assertThat(returnedCandidates.get(0).getId(), is(candidate.getId())); // NOTE until the timestamp issue is fixed
	}

}
