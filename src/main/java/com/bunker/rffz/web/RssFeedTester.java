package com.bunker.rffz.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bunker.rffz.service.retriever.impl.FeedEntryHelper;
import com.sun.syndication.feed.synd.SyndEntryImpl;

@Controller
@RequestMapping(value = "/test")
public class RssFeedTester {

	private FeedEntryHelper feedEntryHelper;

	@Autowired
	public RssFeedTester(FeedEntryHelper feedEntryHelper) {
		this.feedEntryHelper = feedEntryHelper;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void testRssFeed() {
		// String feedSourceUrl = FeedSource.RssFeed.sportRo.url();
		//
		// List<SyndEntryImpl> feedEntries = feedEntryHelper.getFeedEntries(feedSourceUrl);
		// Iterator itr = feedEntries.iterator();
		// while (itr.hasNext()) {
		// SyndEntryImpl element = (SyndEntryImpl) itr.next();
		// createCandidate(element);
		// }
	}
	private void createCandidate(SyndEntryImpl element) {
		String title = element.getTitle();
		String description = element.getDescription().getValue();
		Date date = element.getPublishedDate();
		String url = element.getLink();
		String thumbnailPath = "";

		// Candidate candidate = new Candidate(title, description, date, url, null);
		// candidate.extractImagePath();
		// candidate.cleanUpAnchor();
		// candidate.cleanUpTags();
		//
		// List enclosures = element.getEnclosures();
		// SyndEnclosureImpl syndEnclosure = (SyndEnclosureImpl) enclosures.get(0);
		// if (syndEnclosure.getType().equals("image/jpeg")) {
		// candidate.setImagePath(syndEnclosure.getUrl());
		// }
	}

}
