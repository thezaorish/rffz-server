package com.bunker.rffz.service.retriever.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bunker.rffz.domain.ScheduledTask.Name;
import com.bunker.rffz.domain.retriever.Candidate;
import com.bunker.rffz.domain.retriever.FeedSource;
import com.bunker.rffz.service.job.ScheduledTaskService;
import com.bunker.rffz.service.retriever.CandidateRetrievalService;
import com.bunker.rffz.service.retriever.CandidateService;
import com.bunker.rffz.service.retriever.FeedSourceService;
import com.sun.syndication.feed.synd.SyndEnclosureImpl;
import com.sun.syndication.feed.synd.SyndEntryImpl;

@Service("candidateRetrievalService")
public class CandidateRetrievalServiceImpl implements CandidateRetrievalService {

	private static final Logger logger = Logger.getLogger(CandidateRetrievalServiceImpl.class);

	private CandidateService candidateService;
	private FeedSourceService feedSourceService;
	private ScheduledTaskService scheduledTaskService;

	private FeedEntryHelper feedEntryHelper;

	@Autowired
	public CandidateRetrievalServiceImpl(CandidateService candidateService, FeedSourceService feedSourceService, ScheduledTaskService scheduledTaskService, FeedEntryHelper feedEntryHelper) {
		this.candidateService = candidateService;
		this.feedSourceService = feedSourceService;
		this.scheduledTaskService = scheduledTaskService;
		this.feedEntryHelper = feedEntryHelper;
	}

	@Override
	public void generateCandidates() {
		Date lastRetrievalDate = scheduledTaskService.getLastRun(Name.CandidatesRetrievalJob);

		List<FeedSource> feedSources = feedSourceService.getAllFeedSources();
		for (FeedSource feedSource : feedSources) {
			String feedSourceUrl = feedSource.getUrl();

			List<SyndEntryImpl> feedEntries = feedEntryHelper.getFeedEntries(feedSourceUrl);
			Iterator itr = feedEntries.iterator();
			while (itr.hasNext()) {
				SyndEntryImpl element = (SyndEntryImpl) itr.next();
				try {
					createCandidate(element, feedSource, lastRetrievalDate);
				} catch (Exception ex) {
					logger.warn("generateCandidates: exception occured when creating candidate for " + element);
				}
			}
		}

		scheduledTaskService.updateLastRun(Name.CandidatesRetrievalJob);
	}
	private void createCandidate(SyndEntryImpl element, FeedSource feedSource, Date lastRetrievalDate) {
		if (element.getPublishedDate().before(lastRetrievalDate)) {
			return;
		}

		Candidate candidate = new Candidate(element.getTitle(), element.getDescription().getValue(), element.getPublishedDate(), element.getLink(), feedSource);
		candidate.extractImagePath();
		candidate.cleanUpAnchor();
		candidate.cleanUpTags();

		List enclosures = element.getEnclosures();
		if (enclosures != null && !enclosures.isEmpty()) {
			SyndEnclosureImpl syndEnclosure = (SyndEnclosureImpl) enclosures.get(0);
			if (syndEnclosure.getType().equals("image/jpeg")) {
				candidate.setImagePath(syndEnclosure.getUrl());
			}
		}
		candidateService.createCandidate(candidate);
	}

}
