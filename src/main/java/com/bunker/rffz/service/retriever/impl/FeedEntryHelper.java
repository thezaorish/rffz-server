package com.bunker.rffz.service.retriever.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

	public List<SyndEntryImpl> getFeedEntries(String feedSourceUrl) {
		List<SyndEntryImpl> feedEntries = new ArrayList<SyndEntryImpl>();

		URL url = null;
		try {
			url = new URL(feedSourceUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		SyndFeedInput input = new SyndFeedInput();
		try {
			SyndFeed feed = input.build(new XmlReader(url));
			feedEntries = feed.getEntries();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (FeedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return feedEntries;
	}

}
