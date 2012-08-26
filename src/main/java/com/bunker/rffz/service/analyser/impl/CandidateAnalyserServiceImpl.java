package com.bunker.rffz.service.analyser.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bunker.rffz.domain.ScheduledTask.Name;
import com.bunker.rffz.domain.retriever.Candidate;
import com.bunker.rffz.service.analyser.ArticleService;
import com.bunker.rffz.service.analyser.CandidateAnalyserService;
import com.bunker.rffz.service.job.ScheduledTaskService;
import com.bunker.rffz.service.retriever.CandidateService;

@Service("candidateAnalyserService")
public class CandidateAnalyserServiceImpl implements CandidateAnalyserService {

	private static final Logger logger = Logger.getLogger(CandidateAnalyserServiceImpl.class);

	private CandidateService candidateService;
	private ArticleService articleService;
	private ScheduledTaskService scheduledTaskService;

	@Autowired
	public CandidateAnalyserServiceImpl(CandidateService candidateService, ArticleService articleService, ScheduledTaskService scheduledTaskService) {
		this.candidateService = candidateService;
		this.articleService = articleService;
		this.scheduledTaskService = scheduledTaskService;
	}

	@Override
	public void analyseCandidates() {
		List<Candidate> candidates = candidateService.getUnprocessedCandidates();

		for (Candidate candidate : candidates) {
			try {
				if (candidate.isSteauaRelated()) {
					articleService.createArticle(candidate);
				}

				candidate.setProcessed(true);
				candidateService.updateCandidate(candidate);
			} catch (Exception ex) {
				logger.warn("analyseCandidates: exception occured when analisyng candidate with id " + candidate.getId());
			}
		}
		scheduledTaskService.updateLastRun(Name.CandidatesAnalyserJob);
	}

}
