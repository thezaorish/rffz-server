package com.bunker.rffz.service.scheduledtask;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bunker.rffz.domain.util.ScheduledTask.Name;
import com.bunker.rffz.service.feedarticle.analyser.CandidateAnalyserService;
import com.bunker.rffz.service.scheduledtask.CandidateAnalyserJob;
import com.bunker.rffz.service.util.ScheduledTaskService;

@RunWith(MockitoJUnitRunner.class)
public class CandidateAnalyserJobTest {

	@Mock
	private CandidateAnalyserService candidateAnalyserService;
	@Mock
	private ScheduledTaskService scheduledTaskService;

	private CandidateAnalyserJob candidateAnalyserJob;

	@Before
	public void setUp() {
		candidateAnalyserJob = new CandidateAnalyserJob(candidateAnalyserService, scheduledTaskService);
	}

	@Test
	public void shouldAnalyseCandidatesWhenTaskIsActive() {
		// given the job is active
		given(scheduledTaskService.isActive(Name.CandidatesAnalyserJob)).willReturn(true);

		// when
		candidateAnalyserJob.analyseCandidates();

		// then
		verify(candidateAnalyserService).analyseCandidates();
	}
	@Test
	public void shouldNotAnalyseCandidatesWhenTaskIsDisabled() {
		// given the job is disabled
		given(scheduledTaskService.isActive(Name.CandidatesAnalyserJob)).willReturn(false);

		// when
		candidateAnalyserJob.analyseCandidates();

		// then
		verify(candidateAnalyserService, never()).analyseCandidates();
	}

}
