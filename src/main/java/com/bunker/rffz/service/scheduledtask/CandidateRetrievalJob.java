package com.bunker.rffz.service.scheduledtask;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bunker.rffz.domain.util.ScheduledTask.Name;
import com.bunker.rffz.service.feedarticle.retriever.CandidateRetrievalService;
import com.bunker.rffz.service.util.ScheduledTaskService;

@Component("candidateRetrievalJob")
public class CandidateRetrievalJob {

	private static final Logger logger = Logger.getLogger(CandidateRetrievalJob.class);

	private static final int DELAY_INTERVAL = 65 * 60 * 1000; // every 65 minutes

	private CandidateRetrievalService candidateRetrievalService;

	private ScheduledTaskService scheduledTaskService;

	@Autowired
	public CandidateRetrievalJob(CandidateRetrievalService candidateRetrievalService, ScheduledTaskService scheduledTaskService) {
		this.candidateRetrievalService = candidateRetrievalService;
		this.scheduledTaskService = scheduledTaskService;
	}

	@Scheduled(fixedDelay = DELAY_INTERVAL)
	public void generateCandidates() {
		if (scheduledTaskService.isActive(Name.CandidatesRetrievalJob)) {
			logger.info("generateCandidates: job started");
			candidateRetrievalService.generateCandidates();
			logger.info("generateCandidates: job ended");
		} else {
			logger.info("generateCandidates: job disabled");
		}
	}

}
