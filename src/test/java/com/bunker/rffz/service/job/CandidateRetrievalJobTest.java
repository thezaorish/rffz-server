package com.bunker.rffz.service.job;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bunker.rffz.domain.ScheduledTask.Name;
import com.bunker.rffz.service.retriever.CandidateRetrievalService;

@RunWith(MockitoJUnitRunner.class)
public class CandidateRetrievalJobTest {

	@Mock
	private CandidateRetrievalService candidateRetrievalService;
	@Mock
	private ScheduledTaskService scheduledTaskService;

	private CandidateRetrievalJob candidateRetrievalJob;

	@Before
	public void setUp() {
		candidateRetrievalJob = new CandidateRetrievalJob(candidateRetrievalService, scheduledTaskService);
	}

	@Test
	public void shouldGenerateCandidatesWhenTaskIsActive() {
		// given the job is active
		given(scheduledTaskService.isActive(Name.CandidatesRetrievalJob)).willReturn(true);

		// when
		candidateRetrievalJob.generateCandidates();

		// then
		verify(candidateRetrievalService).generateCandidates();
	}
	@Test
	public void shouldNotGenerateCandidatesWhenTaskIsDisabled() {
		// given the job is disabled
		given(scheduledTaskService.isActive(Name.CandidatesRetrievalJob)).willReturn(false);

		// when
		candidateRetrievalJob.generateCandidates();

		// then
		verify(candidateRetrievalService, never()).generateCandidates();
	}

}
