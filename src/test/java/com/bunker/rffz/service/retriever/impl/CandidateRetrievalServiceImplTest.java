package com.bunker.rffz.service.retriever.impl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bunker.rffz.domain.ScheduledTask;
import com.bunker.rffz.domain.retriever.Candidate;
import com.bunker.rffz.domain.retriever.FeedSource;
import com.bunker.rffz.service.job.ScheduledTaskService;
import com.bunker.rffz.service.retriever.CandidateRetrievalService;
import com.bunker.rffz.service.retriever.CandidateService;
import com.bunker.rffz.service.retriever.FeedSourceService;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEnclosureImpl;
import com.sun.syndication.feed.synd.SyndEntryImpl;

@RunWith(MockitoJUnitRunner.class)
public class CandidateRetrievalServiceImplTest {

	private CandidateRetrievalService candidateRetrievalService;

	@Mock
	private CandidateService candidateService;

	@Mock
	private FeedSourceService feedSourceService;
	@Mock
	private ScheduledTaskService scheduledTaskService;
	@Mock
	private FeedEntryHelper feedEntryHelper;

	@Before
	public void setUp() {
		candidateRetrievalService = new CandidateRetrievalServiceImpl(candidateService, feedSourceService, scheduledTaskService, feedEntryHelper);
	}

	@Test
	public void shouldGenerateCandidateFromFeedWithImageAndSimpleDescription() {
		// given a valid feedSource
		FeedSource feedSource = new FeedSource("name", "url");
		List<FeedSource> feedSources = new ArrayList<FeedSource>(Arrays.asList(feedSource));
		given(feedSourceService.getAllFeedSources()).willReturn(feedSources);

		// and feed entries for the feedSource
		SyndEntryImpl syndEntry = getSyndEntry(true, false);
		List<SyndEntryImpl> syndEntries = new ArrayList<SyndEntryImpl>(Arrays.asList(syndEntry));
		given(feedEntryHelper.getFeedEntries(feedSource.getUrl())).willReturn(syndEntries);

		// when
		candidateRetrievalService.generateCandidates();

		// then a consistent candidate for every valid syndEntry is saved
		verify(candidateService).createCandidate(Matchers.argThat(new CandidateMatcher(syndEntry, feedSource)));
		// and date for last successful retrieval operation is updated
		verify(scheduledTaskService).updateLastRun(ScheduledTask.Name.CandidatesRetrievalJob);
	}
	@Test
	public void shouldGenerateCandidateFromFeedWithoutImageAndSimpleDescription() {
		// given a valid feedSource
		FeedSource feedSource = new FeedSource("name", "url");
		List<FeedSource> feedSources = new ArrayList<FeedSource>(Arrays.asList(feedSource));
		given(feedSourceService.getAllFeedSources()).willReturn(feedSources);

		// and feed entries for the feedSource
		SyndEntryImpl syndEntry = getSyndEntry(false, false);
		List<SyndEntryImpl> syndEntries = new ArrayList<SyndEntryImpl>(Arrays.asList(syndEntry));
		given(feedEntryHelper.getFeedEntries(feedSource.getUrl())).willReturn(syndEntries);

		// when
		candidateRetrievalService.generateCandidates();

		// then a consistent candidate for every valid syndEntry is saved
		verify(candidateService).createCandidate(Matchers.argThat(new CandidateMatcher(syndEntry, feedSource)));
		// and date for last successful retrieval operation is updated
		verify(scheduledTaskService).updateLastRun(ScheduledTask.Name.CandidatesRetrievalJob);
	}
	@Test
	public void shouldGenerateCandidateFromFeedWithoutImageAndSimpleDescription2() {
		// given a valid feedSource
		FeedSource feedSource = new FeedSource("name", "url");
		List<FeedSource> feedSources = new ArrayList<FeedSource>(Arrays.asList(feedSource));
		given(feedSourceService.getAllFeedSources()).willReturn(feedSources);

		// and feed entries for the feedSource
		SyndEntryImpl syndEntry = getSyndEntry(true, false);
		((SyndEnclosureImpl) syndEntry.getEnclosures().get(0)).setType("video");
		List<SyndEntryImpl> syndEntries = new ArrayList<SyndEntryImpl>(Arrays.asList(syndEntry));
		given(feedEntryHelper.getFeedEntries(feedSource.getUrl())).willReturn(syndEntries);

		// when
		candidateRetrievalService.generateCandidates();

		// then a consistent candidate for every valid syndEntry is saved
		verify(candidateService).createCandidate(Matchers.argThat(new CandidateMatcher(syndEntry, feedSource)));
		// and date for last successful retrieval operation is updated
		verify(scheduledTaskService).updateLastRun(ScheduledTask.Name.CandidatesRetrievalJob);
	}
	@Test
	public void shouldGenerateCandidateFromFeedWithImageAndComplexDescription() {
		// given a valid feedSource
		FeedSource feedSource = new FeedSource("name", "url");
		List<FeedSource> feedSources = new ArrayList<FeedSource>(Arrays.asList(feedSource));
		given(feedSourceService.getAllFeedSources()).willReturn(feedSources);

		// and feed entries for the feedSource
		SyndEntryImpl syndEntry = getSyndEntry(true, true);
		List<SyndEntryImpl> syndEntries = new ArrayList<SyndEntryImpl>(Arrays.asList(syndEntry));
		given(feedEntryHelper.getFeedEntries(feedSource.getUrl())).willReturn(syndEntries);

		// when
		candidateRetrievalService.generateCandidates();

		// then a consistent candidate for every valid syndEntry is saved
		verify(candidateService).createCandidate(Matchers.argThat(new CandidateMatcher(syndEntry, feedSource)));
		// and date for last successful retrieval operation is updated
		verify(scheduledTaskService).updateLastRun(ScheduledTask.Name.CandidatesRetrievalJob);
	}
	@Test
	public void shouldGenerateCandidateFromFeedWithoutImageAndComplexDescription() {
		// given a valid feedSource
		FeedSource feedSource = new FeedSource("name", "url");
		List<FeedSource> feedSources = new ArrayList<FeedSource>(Arrays.asList(feedSource));
		given(feedSourceService.getAllFeedSources()).willReturn(feedSources);

		// and feed entries for the feedSource
		SyndEntryImpl syndEntry = getSyndEntry(true, true);
		List<SyndEntryImpl> syndEntries = new ArrayList<SyndEntryImpl>(Arrays.asList(syndEntry));
		given(feedEntryHelper.getFeedEntries(feedSource.getUrl())).willReturn(syndEntries);

		// when
		candidateRetrievalService.generateCandidates();

		// then a consistent candidate for every valid syndEntry is saved
		verify(candidateService).createCandidate(Matchers.argThat(new CandidateMatcher(syndEntry, feedSource)));
		// and date for last successful retrieval operation is updated
		verify(scheduledTaskService).updateLastRun(ScheduledTask.Name.CandidatesRetrievalJob);
	}
	
	private SyndEntryImpl getSyndEntry(boolean withImage, boolean withComplexDescription) {
		SyndEntryImpl syndFeed = new SyndEntryImpl();
		syndFeed.setTitle("Real title.");

		SyndContent syndContent = new SyndContentImpl();
		if (withComplexDescription) {
			syndContent.setValue("<p>Real description.</p> " +
			"<p>" +
			"<a href=\"http://core.ad20.net/click?spgid=0&__x1ts=&pub=62&site=4270&section=0&zone=2008&size=0x0&__x1cguid=1491183028\">" +
			"<img src=\"http://core.ad20.net/ad?__x1ns=1&spgid=0&pub=632203491294404&site=rss.prosport&section=0&zone=RSS_feed&size=728x90&__x1cguid=1078779758\" border=\"0\" alt=\"\"/>" +
			"</a>" +
			"</p>");
		} else {
			syndContent.setValue("Real description.");
		}
		syndFeed.setDescription(syndContent);

		syndFeed.setPublishedDate(new Date(4000));
		syndFeed.setLink("entryLink");

		if (withImage) {
			SyndEnclosureImpl syndEnclosure = new SyndEnclosureImpl();
			syndEnclosure.setType("image/jpeg");
			syndEnclosure.setUrl("linkToImage");
			syndFeed.setEnclosures(new ArrayList(Arrays.asList(syndEnclosure)));
		}

		return syndFeed;
	}

	class CandidateMatcher extends ArgumentMatcher<Candidate> {

		private SyndEntryImpl syndEntry;

		private FeedSource feedSource;

		public CandidateMatcher(SyndEntryImpl syndEntry, FeedSource feedSource) {
			this.syndEntry = syndEntry;
			this.feedSource = feedSource;
		}

		@Override
		public boolean matches(Object argument) {
			if (!(argument instanceof Candidate)) {
				return false;
			}
			Candidate candidate = (Candidate) argument;

			if (!candidate.getTitle().equals(syndEntry.getTitle())) {
				return false;
			}
			if (!candidate.getDescription().equals("Real description.")) {
				return false;
			}
			if (candidate.getPublishDate() != syndEntry.getPublishedDate()) {
				return false;
			}
			if (!candidate.getUrl().equals(syndEntry.getLink())) {
				return false;
			}
			if (!candidate.getFeedSource().equals(feedSource)) {
				return false;
			}
			if (syndEntry.getEnclosures() != null && !syndEntry.getEnclosures().isEmpty()) {
				if (((SyndEnclosureImpl) syndEntry.getEnclosures().get(0)).getType().equals("image/jpeg") && !candidate.getImagePath().equals("linkToImage")) {
					return false;
				}
			}

			return true;
		}

	}

}
