package com.bunker.rffz.service.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bunker.rffz.domain.util.ScheduledTask.Name;
import com.bunker.rffz.service.feedarticle.CandidateAnalyserService;

@Component("candidateAnalyserJob")
public class CandidateAnalyserJob {

	private static final Logger logger = Logger.getLogger(CandidateAnalyserJob.class);

	private static final int DELAY_INTERVAL = 70 * 60 * 1000; // every 70 minutes

	private CandidateAnalyserService candidateAnalyserService;

	private ScheduledTaskService scheduledTaskService;

	@Autowired
	public CandidateAnalyserJob(CandidateAnalyserService candidateAnalyserService, ScheduledTaskService scheduledTaskService) {
		this.candidateAnalyserService = candidateAnalyserService;
		this.scheduledTaskService = scheduledTaskService;
	}

	@Scheduled(fixedDelay = DELAY_INTERVAL)
	public void analyseCandidates() {
		if (scheduledTaskService.isActive(Name.CandidatesAnalyserJob)) {
			logger.info("analyseCandidates: job started");
			candidateAnalyserService.analyseCandidates();
			logger.info("analyseCandidates: job ended");
		} else {
			logger.info("analyseCandidates: job disabled");
		}
	}

}
