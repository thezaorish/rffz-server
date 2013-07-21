package com.bunker.rffz.service.feedarticle.analyser.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * Helper class for interfacing with rome api
 * @author zaorish
 */
@Component("feedEntryHelper")
public class FeedEntryHelper {

	private static final Logger logger = Logger.getLogger(FeedEntryHelper.class);

	public List<SyndEntryImpl> getFeedEntries(String feedSourceUrl) {
		List<SyndEntryImpl> feedEntries = new ArrayList<SyndEntryImpl>();

		URL url;
		try {
			url = new URL(feedSourceUrl);

			SyndFeedInput input = new SyndFeedInput();
			try {
				SyndFeed feed = input.build(new XmlReader(url));
				feedEntries = feed.getEntries();
			} catch (IllegalArgumentException e) {
				logger.warn("getFeedEntries: exception occurred when processing " + feedSourceUrl, e);
			} catch (FeedException e) {
				logger.warn("getFeedEntries: exception occurred when processing " + feedSourceUrl, e);
			} catch (IOException e) {
				logger.warn("getFeedEntries: exception occurred when processing " + feedSourceUrl, e);
			}
			// NOTE multiple catches, I might handle them separately
		} catch (MalformedURLException e) {
			logger.warn("generateCandidates: exception occurred when processing " + feedSourceUrl, e);
		}

		return feedEntries;
	}

}
